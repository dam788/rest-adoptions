package com.restful.adoptions.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    private String user_name;
    private String name;
    private String email;
    private String password;
    private Long phone;
    private Integer role;
    private Boolean active;
    private String avatar_url;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    @PreUpdate
    protected void onUpdate() {
        this.updated_at = new Date();
    }

    @OneToOne
    @JoinColumn(name = "id_location")
    private Location location;

}
