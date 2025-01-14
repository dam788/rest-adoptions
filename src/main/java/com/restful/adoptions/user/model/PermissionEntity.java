package com.restful.adoptions.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permissions")
@Schema(description = "Entidad que representa los permisos dentro del sistema")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del permiso", example = "1")
    private Long idPermission;

    @Column(
            unique = true,
            nullable = false,
            updatable = false
    )
    @Schema(description = "Nombre único del permiso", example = "CREATE, READ, UPDATE ó DELETE")
    private String name;

}
