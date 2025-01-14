package com.restful.adoptions.user.controller;

import com.restful.adoptions.user.controller.dto.AuthCreateUserRequest;
import com.restful.adoptions.user.controller.dto.AuthLoginRequest;
import com.restful.adoptions.user.controller.dto.AuthReponse;
import com.restful.adoptions.user.service.UserDetailServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticación", description = "API para gestionar el registro y login de usuarios")
public class AuthenticationController {

    @Autowired
    private UserDetailServiceImp userDetailService;


    @Operation(summary = "Registro de usuario", description = "Registra un nuevo usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping("/sing-up")
    public ResponseEntity<AuthReponse> register(@RequestBody @Valid AuthCreateUserRequest authCreateUser) throws IllegalAccessException {
        return new ResponseEntity<>(this.userDetailService.createUser(authCreateUser), HttpStatus.CREATED);
    }


    @Operation(summary = "Inicio de sesión", description = "Autentica un usuario y devuelve un token de acceso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario autenticado exitosamente"),
            @ApiResponse(responseCode = "401", description = "Credenciales incorrectas")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthReponse> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }

}
