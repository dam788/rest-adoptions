package com.restful.adoptions.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @NotBlank
    @Size( max = 30 )
    @Column(unique = true)
    private String username;

    @NotBlank
    @Email
    @Size( max = 30 )
    private String email;

    @NotBlank
    @JsonIgnore
    private String password;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @JsonIgnore
    @Column(name = "account_No_Expired")
    private boolean accountNoExpired;

    @JsonIgnore
    @Column(name = "account_No_Locked")
    private boolean accountNoLocked;

    @JsonIgnore
    @Column(name = "credential_No_Expired")
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
    private Set<RoleEntity> roleEntities = new HashSet<>();

}
