package com.restful.adoptions.petimage.controller;

import com.restful.adoptions.petimage.model.PetImageEntity;
import com.restful.adoptions.petimage.service.PetImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
public class PetImageController {

    @Autowired
    PetImageService petImageService;



    @GetMapping
    public ResponseEntity <List<PetImageEntity>> getPetImages () {

        return ResponseEntity.ok( petImageService.getAllPetImages() );

    }

    @PostMapping
    public ResponseEntity<PetImageEntity> createPetImage (@RequestBody PetImageEntity petImageEntity, UriComponentsBuilder ucb ) {

        PetImageEntity imageSaved = petImageService.createOnePickImage(petImageEntity);
        URI uriImage = ucb
                .path( "/api/v1/images/{id}" )
                .buildAndExpand(imageSaved.getIdImage())
                .toUri();

        return ResponseEntity.created( uriImage ).body( imageSaved );

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteImage ( @PathVariable Long id ) {

        petImageService.deletePetImageById( id );

        return ResponseEntity.ok().body("Was deleted image with id " + id);

    }

}
