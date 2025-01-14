package com.restful.adoptions.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
@Schema(description = "Entidad que representa un rol dentro del sistema")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del rol", example = "1")
    private Long idRole;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Nombre del rol", example = "USER, ADMIN, DEV ó REFUGE")
    private RoleEnum roleEnum;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name= "id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_permission")
    )
    @Schema(description = "Lista de permisos asociados al rol")
    private Set<PermissionEntity> permissionEntityList = new HashSet<>();

}
