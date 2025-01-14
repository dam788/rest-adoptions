package com.restful.adoptions.user.controller;

import com.restful.adoptions.user.controller.dto.UserDTO;
import com.restful.adoptions.user.model.UserEntity;
import com.restful.adoptions.user.service.UserDetailServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Usuarios", description = "API para gestionar usuarios en la plataforma")
public class UserController {

    @Autowired
    private UserDetailServiceImp userService;


    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista de todos los usuarios registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {

        List<UserDTO> users = userService.getAllUsers()
                .stream()
                .map(userService::convertToUserDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);

    }

    @Operation(summary = "Obtener un usuario por ID", description = "Devuelve los detalles de un usuario específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {

        Optional<UserEntity> user = userService.getUserById(id);

        return user.map(userDTO -> ResponseEntity.ok(userService.convertToUserDTO(userDTO)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

}
