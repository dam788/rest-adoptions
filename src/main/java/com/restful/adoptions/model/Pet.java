package com.restful.adoptions.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name= "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pet;

    private String name;
    private String description;

    private int birthday;
    private double weight;
    private String size;
    private String gender;
    private boolean active;
    private boolean available;
    private String avatar_url;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at = new Date();

    // falta relaciones
    //private Long id_user;
    //private Long id_species;
    //private Long id_pet_image;
    //private Long id_location;

}
