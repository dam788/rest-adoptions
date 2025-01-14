package com.restful.adoptions.petimage.controller;

import com.restful.adoptions.petimage.model.PetImageEntity;
import com.restful.adoptions.petimage.service.PetImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
@Tag(name = "Imagenes mascotas", description = "Controlador para gestionar imágenes de mascotas en adopción")
public class PetImageController {

    @Autowired
    PetImageService petImageService;



    @GetMapping
    @Operation(summary = "Obtener todas las imágenes", description = "Devuelve una lista de todas las imágenes de mascotas almacenadas en el sistema.")
    public ResponseEntity <List<PetImageEntity>> getPetImages () {

        return ResponseEntity.ok( petImageService.getAllPetImages() );

    }

    @PostMapping
    @Operation(summary = "Subir una imagen de mascota", description = "Guarda una nueva imagen de mascota en el sistema y devuelve la entidad creada.")
    public ResponseEntity<PetImageEntity> createPetImage(
            @RequestBody @Parameter(description = "Entidad de imagen a registrar") PetImageEntity petImageEntity,
            UriComponentsBuilder ucb
    ) {

        PetImageEntity imageSaved = petImageService.createOnePickImage(petImageEntity);
        URI uriImage = ucb
                .path( "/api/v1/images/{id}" )
                .buildAndExpand(imageSaved.getIdImage())
                .toUri();

        return ResponseEntity.created( uriImage ).body( imageSaved );

    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar una imagen", description = "Elimina una imagen de mascota identificada por su ID.")
    public ResponseEntity<String> deleteImage(
            @PathVariable @Parameter(description = "ID de la imagen a eliminar") Long id
    ) {

        petImageService.deletePetImageById( id );

        return ResponseEntity.ok().body("Was deleted image with id " + id);

    }

}
