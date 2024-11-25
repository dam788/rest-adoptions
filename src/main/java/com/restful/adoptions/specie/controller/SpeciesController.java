package com.restful.adoptions.specie.controller;

import com.restful.adoptions.specie.model.SpeciesEntity;
import com.restful.adoptions.specie.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/api/v1/species")
public class SpeciesController {

    @Autowired
    private SpeciesService speciesService;

    @GetMapping
    public ResponseEntity <List<SpeciesEntity>> getSpecies() {
        return ResponseEntity.ok( speciesService.getAllSpecies() );
    }

    @PostMapping
    public ResponseEntity <SpeciesEntity> createSpecies (@RequestBody SpeciesEntity speciesEntity, UriComponentsBuilder ucb) {

        SpeciesEntity speciesEntitySaved = speciesService.createOneSpecies(speciesEntity);
        URI uriSpecies = ucb
                .path("/api/v1/species/{id}")
                .buildAndExpand(speciesEntitySaved.getIdSpecies())
                .toUri();

        return ResponseEntity.created( uriSpecies ).body(speciesEntitySaved);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> updateSpecies(@RequestBody SpeciesEntity updatedSpeciesEntity, @PathVariable Long id) {

        return ResponseEntity.ok (

                speciesService.getSpeciesById(id)
                        .map(speciesEntity -> {
                            speciesEntity.setName(updatedSpeciesEntity.getName());
                            speciesService.updateOneSpecies(speciesEntity);

                            return ResponseEntity.ok(speciesEntity);

                        }).orElseGet(() -> ResponseEntity.notFound().build())

        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSpecies(@PathVariable Long id) {

        speciesService.deleteOneSpeciesById(id);

        return ResponseEntity.ok().body("Was delete species with id " + id);
    }

}
