package com.restful.adoptions.adoption.model;

import com.restful.adoptions.pet.model.PetEntity;
import com.restful.adoptions.user.model.UserEntity;
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
@Table(name= "adoptions")
@Schema(description = "Entidad que representa una adopción de una mascota por un usuario")
public class AdoptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la adopción", example = "1")
    private Long idAdoption;

    @ManyToOne
    @JoinColumn(name = "idPet", nullable = false)
    @Schema(description = "Mascota que está siendo adoptada")
    private PetEntity pet;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    @Schema(description = "Usuario que adopta la mascota")
    private UserEntity user;

    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Fecha en que se realizó la adopción", example = "2025-01-01T00:00:00")
    private Date adoption_date = new Date();

}
