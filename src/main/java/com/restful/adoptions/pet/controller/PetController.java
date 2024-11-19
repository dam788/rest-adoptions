package com.restful.adoptions.pet.controller;

import com.restful.adoptions.pet.model.Pet;
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
    public ResponseEntity<List<Pet>> getPets () {

        return ResponseEntity.ok( petService.getAllPets() );

    }

    @GetMapping("/{id}")
    public ResponseEntity <Optional<Pet>> getPet (@PathVariable Long id ) {

        return ResponseEntity.ok( petService.getPetById(id) );

    }

    @PostMapping
    public ResponseEntity <Pet> createPet (@RequestBody Pet pet, UriComponentsBuilder ucb ) {

        Pet petSaved = petService.createOnePet(pet);
        URI uriPet = ucb
                .path("/api/v1/pets/{id}")
                .buildAndExpand(petSaved.getIdPet())
                .toUri();

        return ResponseEntity.created( uriPet ).body( petSaved );

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePet(@RequestBody Pet updatedPet, @PathVariable Long id) {

        return ResponseEntity.ok (

                petService.getPetById(id)
                        .map(pet -> {

                            pet.setName( updatedPet.getName() );
                            pet.setDescription( updatedPet.getDescription() );
                            pet.setBirthday( updatedPet.getBirthday() );
                            pet.setWeight( updatedPet.getWeight() );
                            pet.setSize( updatedPet.getSize() );
                            pet.setGender( updatedPet.getGender() );
                            pet.setAvailable( true );
                            pet.setAvatarUrl( updatedPet.getAvatarUrl() );
                            pet.setSpecies( updatedPet.getSpecies() );
                            pet.setPetImages( updatedPet.getPetImages() );
                            pet.setLocation( updatedPet.getLocation() );
                            petService.updateOnePet(pet);

                            return ResponseEntity.ok(pet);

                        }).orElseGet(() -> {

                            return ResponseEntity.notFound().build();

                        })

        );
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Long id) {

        return ResponseEntity.ok (

                petService.getPetById(id)
                        .map(pet -> {

                            pet.setActive( false );
                            petService.deleteOnePet(pet);

                            return ResponseEntity.ok(pet);

                        }).orElseGet(() -> {

                            return ResponseEntity.notFound().build();

                        })

        );
    }

    @PutMapping("/not-available/{id}")
    public ResponseEntity<?> notAvailablePet(@PathVariable Long id) {

        return ResponseEntity.ok (

                petService.getPetById(id)
                        .map(pet -> {

                            pet.setAvailable( false );
                            petService.notAvailablePet(pet);

                            return ResponseEntity.ok(pet);

                        }).orElseGet(() -> {

                            return ResponseEntity.notFound().build();

                        })

        );
    }


}

