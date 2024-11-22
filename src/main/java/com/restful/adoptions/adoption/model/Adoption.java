package com.restful.adoptions.adoption.model;

import com.restful.adoptions.pet.model.Pet;
import com.restful.adoptions.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "adoptions")
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdoption;

    @ManyToOne
    @JoinColumn(name = "idPet")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date adoption_date = new Date();

}
