package com.restful.adoptions.petimage.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "images")
@Schema(description = "Entidad que representa una imagen de una mascota en el sistema de adopciones")
public class PetImageEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la imagen", example = "1")
    private Long idImage;

    @Column(unique = true, nullable = false)
    @Schema(description = "URL de la imagen almacenada", example = "https://example.com/images/pet123.jpg")
    private String imageUrl;

}
