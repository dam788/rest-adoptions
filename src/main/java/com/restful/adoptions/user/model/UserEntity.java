package com.restful.adoptions.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@Table(name = "users")
@Schema(description = "Entidad que representa a un usuario en la plataforma")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del usuario", example = "1")
    private Long idUser;

    @NotBlank
    @Size( max = 30 )
    @Column(unique = true)
    @Schema(description = "Nombre de usuario único", example = "johndoe")
    private String username;

    @NotBlank
    @Email
    @Size( max = 30 )
    @Schema(description = "Correo electrónico del usuario", example = "johndoe@example.com")
    private String email;

    @NotBlank
    @JsonIgnore
    @Schema(description = "Contraseña del usuario (oculta en respuestas)", example = "********")
    private String password;

    @Column(name = "is_enabled")
    @Schema(description = "Indica si la cuenta está habilitada", example = "true")
    private boolean isEnabled;

    @JsonIgnore
    @Column(name = "account_No_Expired")
    @Schema(description = "Indica si la cuenta no ha expirado", example = "true")
    private boolean accountNoExpired;

    @JsonIgnore
    @Column(name = "account_No_Locked")
    @Schema(description = "Indica si la cuenta no está bloqueada", example = "true")
    private boolean accountNoLocked;

    @JsonIgnore
    @Column(name = "credential_No_Expired")
    @Schema(description = "Indica si las credenciales no han expirado", example = "true")
    private boolean credentialNoExpired;

    @ManyToMany(
            targetEntity = RoleEntity.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    @Schema(description = "Lista de roles asignados al usuario")
    private Set<RoleEntity> roleEntities = new HashSet<>();

}
