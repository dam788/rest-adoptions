package com.restful.adoptions.user.controller;

import com.restful.adoptions.user.controller.dto.UserDTO;
import com.restful.adoptions.user.model.UserEntity;
import com.restful.adoptions.user.service.UserDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("deniedAll()")
public class UserController {

    @Autowired
    private UserDetailServiceImp userService;


    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<UserDTO>> getUsers() {

        List<UserDTO> users = userService.getAllUsers()
                .stream()
                .map(userService::convertToUserDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {

        Optional<UserEntity> user = userService.getUserById(id);

        return user.map(userDTO -> ResponseEntity.ok(userService.convertToUserDTO(userDTO)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    @PreAuthorize("hasAuthority('READ', 'CREATE')")
    public ResponseEntity <URI> createUser (@RequestBody UserEntity userEntity, UriComponentsBuilder ucb ) {

        UserEntity userEntitySaved = userService.createOneUser(userEntity);
        URI uriUser = ucb
                .path("/api/v1/users/{id}")
                .buildAndExpand(userEntitySaved.getIdUser())
                .toUri();

        return ResponseEntity.created( uriUser ).body(uriUser);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('READ', 'UPDATE')")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity updatedUserEntity, @PathVariable Long id) {

        return ResponseEntity.ok (

                userService.getUserById(id)
                    .map(user -> {

                        user.setUsername( updatedUserEntity.getUsername() );
                        user.setPassword( updatedUserEntity.getPassword() );
                        userService.updateOneUser(user);

                        return ResponseEntity.ok( user );

                    }).orElseGet(() -> {

                        return ResponseEntity.notFound().build();

                    })

        );
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('READ', 'DELETE')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        return ResponseEntity.ok (

                userService.getUserById(id)
                        .map(user -> {

                            user.setEnabled( false );

                            userService.deleteOneUser(user);
                            return ResponseEntity.ok(user);

                        }).orElseGet(() -> {

                            return ResponseEntity.notFound().build();

                        })

        );
    }

}
