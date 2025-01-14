package com.restful.adoptions.specie.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "species")
@Schema(description = "Entidad que representa una especie de animal en el sistema de adopciones")
public class SpeciesEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la especie", example = "1")
    private Long idSpecies;

    @Column(nullable = false, unique = true)
    @Schema(description = "Nombre de la especie", example = "Perro")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Fecha de creación de la especie", example = "2024-01-14T10:30:00Z")
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Fecha de última actualización de la especie", example = "2024-01-15T15:45:00Z")
    private Date updatedAt;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

}
