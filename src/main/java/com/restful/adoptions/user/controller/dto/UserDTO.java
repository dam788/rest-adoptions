package com.restful.adoptions.user.controller.dto;

import com.restful.adoptions.user.model.RoleEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

public record UserDTO (
        @NotBlank Long idUser,
        @NotBlank String username,
        @NotBlank String email,
        @NotBlank Set<RoleEntity> roleEntities
    ){}
