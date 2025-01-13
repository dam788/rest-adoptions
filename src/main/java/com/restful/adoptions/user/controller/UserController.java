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

}
