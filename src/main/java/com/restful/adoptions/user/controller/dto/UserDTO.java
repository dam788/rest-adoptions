package com.restful.adoptions.user.controller.dto;

import com.restful.adoptions.user.model.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long idUser;
    private String username;
    private String email;
    private boolean isEnabled;
    private Set<RoleEntity> roleEntities;
}
