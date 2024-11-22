package com.restful.adoptions.pet.controller;

import com.restful.adoptions.pet.model.PetEntity;
import com.restful.adoptions.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pets")
public class PetController {

    @Autowired
    PetService petService;



    @GetMapping
    public ResponseEntity<List<PetEntity>> getPets () {

        return ResponseEntity.ok( petService.getAllPets() );

    }

    @GetMapping("/{id}")
    public ResponseEntity <Optional<PetEntity>> getPet (@PathVariable Long id ) {

        return ResponseEntity.ok( petService.getPetById(id) );

    }

    @PostMapping
    public ResponseEntity <PetEntity> createPet (@RequestBody PetEntity petEntity, UriComponentsBuilder ucb ) {

        PetEntity petEntitySaved = petService.createOnePet(petEntity);
        URI uriPet = ucb
                .path("/api/v1/pets/{id}")
                .buildAndExpand(petEntitySaved.getIdPet())
                .toUri();

        return ResponseEntity.created( uriPet ).body(petEntitySaved);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePet(@RequestBody PetEntity updatedPetEntity, @PathVariable Long id) {

        return ResponseEntity.ok (

                petService.getPetById(id)
                        .map(petEntity -> {

                            petEntity.setName( updatedPetEntity.getName() );
                            petEntity.setDescription( updatedPetEntity.getDescription() );
                            petEntity.setBirthday( updatedPetEntity.getBirthday() );
                            petEntity.setWeight( updatedPetEntity.getWeight() );
                            petEntity.setSize( updatedPetEntity.getSize() );
                            petEntity.setGender( updatedPetEntity.getGender() );
                            petEntity.setAvailable( true );
                            petEntity.setAvatarUrl( updatedPetEntity.getAvatarUrl() );
                            petEntity.setSpeciesEntity( updatedPetEntity.getSpeciesEntity() );
                            petEntity.setPetImageEntities( updatedPetEntity.getPetImageEntities() );
                            petEntity.setLocationEntity( updatedPetEntity.getLocationEntity() );
                            petService.updateOnePet(petEntity);

                            return ResponseEntity.ok(petEntity);

                        }).orElseGet(() -> {

                            return ResponseEntity.notFound().build();

                        })

        );
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Long id) {

        return ResponseEntity.ok (

                petService.getPetById(id)
                        .map(petEntity -> {

                            petEntity.setActive( false );
                            petService.deleteOnePet(petEntity);

                            return ResponseEntity.ok(petEntity);

                        }).orElseGet(() -> {

                            return ResponseEntity.notFound().build();

                        })

        );
    }

    @PutMapping("/not-available/{id}")
    public ResponseEntity<?> notAvailablePet(@PathVariable Long id) {

        return ResponseEntity.ok (

                petService.getPetById(id)
                        .map(petEntity -> {

                            petEntity.setAvailable( false );
                            petService.notAvailablePet(petEntity);

                            return ResponseEntity.ok(petEntity);

                        }).orElseGet(() -> {

                            return ResponseEntity.notFound().build();

                        })

        );
    }


}

