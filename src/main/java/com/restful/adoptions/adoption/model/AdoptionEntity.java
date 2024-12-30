package com.restful.adoptions.adoption.model;

import com.restful.adoptions.pet.model.PetEntity;
import com.restful.adoptions.user.model.UserEntity;
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
public class AdoptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdoption;

    @ManyToOne
    @JoinColumn(name = "idPet")
    private PetEntity pet;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date adoption_date = new Date();

}
