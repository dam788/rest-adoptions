package com.restful.adoptions.petimage.model;

import jakarta.persistence.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetImage petImage = (PetImage) o;
        return Objects.equals(idPetImage, petImage.idPetImage) && Objects.equals(imageUrl, petImage.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPetImage, imageUrl);
    }
}
