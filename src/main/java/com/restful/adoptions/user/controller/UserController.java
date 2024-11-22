package com.restful.adoptions.user.controller;

import com.restful.adoptions.user.model.UserEntity;
import com.restful.adoptions.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("deniedAll()")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity <List <UserEntity> > getUsers () {

        return ResponseEntity.ok( userService.getAllUsers() );

    }

    @GetMapping("/{id}")
    public ResponseEntity <Optional <UserEntity> > getUser (@PathVariable Long id ) {

        return ResponseEntity.ok( userService.getUserById(id) );

    }

    @PostMapping
    public ResponseEntity <URI> createUser (@RequestBody UserEntity userEntity, UriComponentsBuilder ucb ) {

        UserEntity userEntitySaved = userService.createOneUser(userEntity);
        URI uriUser = ucb
                .path("/api/v1/users/{id}")
                .buildAndExpand(userEntitySaved.getIdUser())
                .toUri();

        return ResponseEntity.created( uriUser ).body(uriUser);

    }

    @PutMapping("/{id}")
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
