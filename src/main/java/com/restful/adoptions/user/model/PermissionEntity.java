package com.restful.adoptions.user.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permissions")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermission;

    @Column(
            unique = true,
            nullable = false,
            updatable = false
    )
    private String name;

}
