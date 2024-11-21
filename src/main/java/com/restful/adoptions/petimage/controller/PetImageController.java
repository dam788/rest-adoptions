package com.restful.adoptions.petimage.controller;

import com.restful.adoptions.petimage.model.PetImage;
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
    public ResponseEntity <List<PetImage>> getPetImages () {

        return ResponseEntity.ok( petImageService.getAllPetImages() );

    }

    @PostMapping
    public ResponseEntity<PetImage> createPetImage (@RequestBody PetImage petImage, UriComponentsBuilder ucb ) {

        PetImage imageSaved = petImageService.createOnePickImage(petImage);
        URI uriImage = ucb
                .path( "/api/v1/images/{id}" )
                .buildAndExpand( imageSaved.getIdPetImage() )
                .toUri();

        return ResponseEntity.created( uriImage ).body( imageSaved );

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteImage ( @PathVariable Long id ) {

        petImageService.deletePetImageById( id );

        return ResponseEntity.ok().body("Was deleted image with id " + id);

    }

}