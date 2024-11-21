package com.restful.adoptions.user.controller;

import com.restful.adoptions.user.model.User;
import com.restful.adoptions.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping
    public ResponseEntity <List <User> > getUsers () {

        return ResponseEntity.ok( userService.getAllUsers() );

    }

    @GetMapping("/{id}")
    public ResponseEntity <Optional <User> > getUser ( @PathVariable Long id ) {

        return ResponseEntity.ok( userService.getUserById(id) );

    }


    @PostMapping
    public ResponseEntity <URI> createUser ( @RequestBody User user, UriComponentsBuilder ucb ) {

        User userSaved = userService.createOneUser(user);
        URI uriUser = ucb
                .path("/api/v1/users/{id}")
                .buildAndExpand(userSaved.getIdUser())
                .toUri();

        return ResponseEntity.created( uriUser ).body(uriUser);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser, @PathVariable Long id) {

        return ResponseEntity.ok (

                userService.getUserById(id)
                    .map(user -> {

                        user.setUserName( updatedUser.getUserName() );
                        user.setName( updatedUser.getName() );
                        user.setPassword( updatedUser.getPassword() );
                        user.setPhone( updatedUser.getPhone() );
                        user.setAvatarUrl( updatedUser.getAvatarUrl() );
                        user.setLocation( updatedUser.getLocation() );
                        user.setCreatedPets( updatedUser.getCreatedPets() );
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

                            user.setActive( false );

                            userService.deleteOneUser(user);
                            return ResponseEntity.ok(user);

                        }).orElseGet(() -> {

                            return ResponseEntity.notFound().build();

                        })

        );
    }

}