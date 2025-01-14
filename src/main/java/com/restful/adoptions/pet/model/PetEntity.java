package com.restful.adoptions.pet.model;

import com.restful.adoptions.location.model.LocationEntity;
import com.restful.adoptions.petimage.model.PetImageEntity;
import com.restful.adoptions.specie.model.SpeciesEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "pets")
@Schema(description = "Entidad que representa una mascota en adopción")
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la mascota", example = "1")
    private Long idPet;

    @Schema(description = "Nombre de la mascota", example = "Luna")
    private String name;

    @Schema(description = "Descripción de la mascota", example = "Es una perrita juguetona y cariñosa")
    private String description;

    @Schema(description = "Año de nacimiento de la mascota", example = "2018")
    private Integer birthday;

    @Schema(description = "Peso de la mascota en kg", example = "12.5")
    private Double weight;

    @Schema(description = "Tamaño de la mascota", example = "Mediano")
    private String size;

    @Schema(description = "Género de la mascota", example = "Hembra")
    private String gender;

    @Schema(description = "Indica si la mascota está activa en la plataforma", example = "true")
    private Boolean active;

    @Schema(description = "Indica si la mascota está disponible para adopción", example = "true")
    private Boolean available;

    @Schema(description = "URL del avatar de la mascota", example = "https://example.com/images/luna.jpg")
    private String avatarUrl;

    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Fecha de creación del registro", example = "2024-01-01T12:00:00")
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Fecha de última actualización del registro", example = "2024-01-10T15:30:00")
    private Date updatedAt;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    @OneToOne
    @JoinColumn(name = "idSpecies")
    @Schema(description = "Especie a la que pertenece la mascota")
    private SpeciesEntity species;

    @OneToOne
    @JoinColumn( name = "idLocation" )
    private LocationEntity location;

    @OneToMany(
            targetEntity = PetImageEntity.class,
            fetch = FetchType.EAGER,
            mappedBy = "pet"
    )
    @Schema(description = "Lista de imágenes asociadas a la mascota")
    private List<PetImageEntity> images;

}
