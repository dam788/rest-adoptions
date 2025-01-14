package com.restful.adoptions.user.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(
        @NotBlank String username,
        @NotBlank String email,
        @NotBlank String password,
        @Valid AuthCreateRoleRequest roleRequest){
}
