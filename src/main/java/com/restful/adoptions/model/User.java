package com.restful.adoptions.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String userName;
    private String name;
    private String email;
    private String password;
    private Long phone;
    private Integer role;
    private Boolean active;
    private String avatarUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    @OneToMany
    private List<Pet> createdPets;

    @OneToOne
    @JoinColumn(name = "idLocation")
    private Location location;

}
