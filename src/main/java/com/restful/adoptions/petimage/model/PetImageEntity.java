package com.restful.adoptions.petimage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "images")
public class PetImageEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idImage;

    private String imageUrl;

}
