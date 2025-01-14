package com.restful.adoptions.pet.controller;

import com.restful.adoptions.pet.model.PetEntity;
import com.restful.adoptions.pet.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pets")
@Tag(name = "Mascotas", description = "Controlador para la gestión de mascotas en adopción")
public class PetController {

    @Autowired
    PetService petService;



    @GetMapping
    @Operation(summary = "Obtener todas las mascotas", description = "Devuelve una lista de todas las mascotas disponibles en la base de datos")
    @ApiResponse(responseCode = "200", description = "Lista de mascotas obtenida exitosamente")
    public ResponseEntity<List<PetEntity>> getPets () {

        return ResponseEntity.ok( petService.getAllPets() );

    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una mascota por ID", description = "Devuelve una mascota específica basada en su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mascota encontrada"),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    public ResponseEntity <Optional<PetEntity>> getPet (@PathVariable Long id ) {

        return ResponseEntity.ok( petService.getPetById(id) );

    }

    @PostMapping
    @Operation(summary = "Crear una nueva mascota", description = "Agrega una nueva mascota a la base de datos")
    @ApiResponse(responseCode = "201", description = "Mascota creada exitosamente")
    public ResponseEntity <PetEntity> createPet (@RequestBody PetEntity petEntity, UriComponentsBuilder ucb ) {

        PetEntity petEntitySaved = petService.createOnePet(petEntity);
        URI uriPet = ucb
                .path("/api/v1/pets/{id}")
                .buildAndExpand(petEntitySaved.getIdPet())
                .toUri();

        return ResponseEntity.created( uriPet ).body(petEntitySaved);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una mascota", description = "Modifica la información de una mascota existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mascota actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    public ResponseEntity<?> updatePet(
            @RequestBody PetEntity updatedPetEntity,
            @Parameter(description = "ID de la mascota a actualizar", example = "1") @PathVariable Long id
    ) {

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
                            petEntity.setSpecies(updatedPetEntity.getSpecies());
                            petEntity.setImages(updatedPetEntity.getImages());
                            petEntity.setLocation(updatedPetEntity.getLocation());
                            petService.updateOnePet(petEntity);

                            return ResponseEntity.ok(petEntity);

                        }).orElseGet(() -> {

                            return ResponseEntity.notFound().build();

                        })

        );
    }

    @PutMapping("/delete/{id}")
    @Operation(summary = "Desactivar una mascota", description = "Marca una mascota como inactiva en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mascota desactivada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    public ResponseEntity<?> deletePet(
            @Parameter(description = "ID de la mascota a desactivar", example = "1") @PathVariable Long id
    ) {

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
    @Operation(summary = "Marcar una mascota como no disponible", description = "Indica que la mascota ya no está disponible para adopción")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mascota marcada como no disponible"),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    public ResponseEntity<?> notAvailablePet(
            @Parameter(description = "ID de la mascota a modificar", example = "1") @PathVariable Long id
    ) {

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

