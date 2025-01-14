package com.restful.adoptions.location.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "locations")
@Schema(description = "Entidad que representa la ubicación de una mascota en adopción.")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la ubicación, autoincrementable.")
    private Long idLocation;

    @Schema(description = "Provincia donde se encuentra la mascota.")
    private String province;

    @Schema(description = "Ciudad donde se encuentra la mascota.")
    private String city;

    @Schema(description = "Longitud geográfica de la ubicación.")
    private Float lon;

    @Schema(description = "Latitud geográfica de la ubicación.")
    private Float lat;

}
