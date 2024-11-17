package com.restful.adoptions.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name= "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPet;

    private String name;
    private String description;
    private Integer birthday;
    private Double weight;
    private String size;
    private String gender;
    private Boolean active;
    private Boolean available;
    private String avatarUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    @OneToOne
    @JoinColumn(name = "idSpecies")
    private Species species;

    @OneToOne
    @JoinColumn(name = "idLocation")
    private Location location;

    @OneToMany
    private List<PetImage> petImages;

}
