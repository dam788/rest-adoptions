package com.restful.adoptions.pet.model;

import com.restful.adoptions.location.model.LocationEntity;
import com.restful.adoptions.petimage.model.PetImageEntity;
import com.restful.adoptions.specie.model.SpeciesEntity;
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
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPet;

    private String name;
    private String description;
    private Integer birthday;
    private Double weight;
    private String size;
    private String gender;
    private Boolean active;
    private Boolean available;
    private String avatarUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    @OneToOne
    @JoinColumn(name = "idSpecies")
    private SpeciesEntity speciesEntity;

    @OneToOne
    @JoinColumn( name = "idLocation" )
    private LocationEntity locationEntity;

    @JoinTable(
            name = "pets_images",
            joinColumns = @JoinColumn(name = "pet"),
            inverseJoinColumns = @JoinColumn(name = "image")
    )
    @OneToMany
    private List<PetImageEntity> petImageEntities;

}
