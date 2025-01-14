package com.restful.adoptions.user.controller;

import com.restful.adoptions.user.controller.dto.AuthCreateUserRequest;
import com.restful.adoptions.user.controller.dto.AuthLoginRequest;
import com.restful.adoptions.user.controller.dto.AuthReponse;
import com.restful.adoptions.user.service.UserDetailServiceImp;
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
public class AuthenticationController {

    @Autowired
    private UserDetailServiceImp userDetailService;

    @PostMapping("/sing-up")
    public ResponseEntity<AuthReponse> register(@RequestBody @Valid AuthCreateUserRequest authCreateUser) throws IllegalAccessException {
        return new ResponseEntity<>(this.userDetailService.createUser(authCreateUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthReponse> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }

}
