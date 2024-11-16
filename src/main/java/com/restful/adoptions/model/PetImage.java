package com.restful.adoptions.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table( name = "pet_images")
public class PetImage {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idPetImage;

    private String imageUrl;

}
