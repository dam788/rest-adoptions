package com.restful.adoptions.controller;

import com.restful.adoptions.model.Species;
import com.restful.adoptions.service.SpeciesService;
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
    public ResponseEntity <List<Species>> getSpecies() {
        return ResponseEntity.ok( speciesService.getAllSpecies() );
    }

    @PostMapping
    public ResponseEntity <URI> createSpecies (@RequestBody Species species, UriComponentsBuilder ucb) {

        Species speciesSaved = speciesService.createOneSpecies(species);
        URI uriSpecies = ucb
                .path("/api/v1/species/{id}")
                .buildAndExpand(speciesSaved.getIdSpecies())
                .toUri();

        return ResponseEntity.created( uriSpecies ).body(uriSpecies);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> updateSpecies(@RequestBody Species updatedSpecies, @PathVariable Long id) {

        return ResponseEntity.ok (

                speciesService.getSpeciesById(id)
                        .map(species -> {
                            species.setName(updatedSpecies.getName());
                            speciesService.updateOneSpecies(species);

                            return ResponseEntity.ok(species);

                        }).orElseGet(() -> ResponseEntity.notFound().build())

        );
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteSpecies(@PathVariable Long id) {

        speciesService.deleteOneSpeciesById(id);

        return ResponseEntity.ok().body("Was delete species with id " + id);
    }

}
