package com.restful.adoptions.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name= "adoptions")
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_adoption;

    // falta relaciones





    @Temporal(TemporalType.TIMESTAMP)
    private Date adoption_date = new Date();
    
}
