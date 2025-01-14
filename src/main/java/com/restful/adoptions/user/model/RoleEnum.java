package com.restful.adoptions.user.model;

import io.swagger.v3.oas.annotations.media.Schema;

public enum RoleEnum {
    @Schema(description = "Rol de administrador con todos los permisos")
    ADMIN,

    @Schema(description = "Rol de usuario con permisos básicos")
    USER,

    @Schema(description = "Rol de desarrollador con permisos técnicos")
    DEV,

    @Schema(description = "Rol de refugio para gestionar adopciones")
    REFUGE
}
