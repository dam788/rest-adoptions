package com.restful.adoptions.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table( name = "pet_images")
public class PetImage {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idPetImage;

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getIdPetImage() {
        return idPetImage;
    }

    public void setIdPetImage(Long idPetImage) {
        this.idPetImage = idPetImage;
    }
}
