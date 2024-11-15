package com.restful.adoptions.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table( name = "species")
public class Species {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id_species;

    private String species_name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at = new Date();

}
