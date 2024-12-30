package com.restful.adoptions.petimage.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table( name = "pet_images")
public class PetImageEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idPetImage;

    private String imageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetImageEntity petImageEntity = (PetImageEntity) o;
        return Objects.equals(idPetImage, petImageEntity.idPetImage) && Objects.equals(imageUrl, petImageEntity.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPetImage, imageUrl);
    }
}
