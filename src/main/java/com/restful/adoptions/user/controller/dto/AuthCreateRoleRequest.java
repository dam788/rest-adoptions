package com.restful.adoptions.user.controller.dto;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record AuthCreateRoleRequest(
        @Size (max = 3, message = "the user cannot have more than 3 roles") List<String> roleListName){
}
