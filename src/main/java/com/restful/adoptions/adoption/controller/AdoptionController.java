package com.restful.adoptions.adoption.controller;

import com.restful.adoptions.adoption.model.AdoptionEntity;
import com.restful.adoptions.adoption.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/adoptions")
public class AdoptionController {

    @Autowired
    AdoptionService adoptionService;


    @GetMapping
    public ResponseEntity<List <AdoptionEntity> > getAllAdoptions() {

        return ResponseEntity.ok( adoptionService.getAllAdoptions() );

    }

    @PostMapping
    public ResponseEntity<AdoptionEntity> createAdoption(@RequestBody AdoptionEntity adoptionEntity, UriComponentsBuilder ucb) {

        AdoptionEntity adoptionEntitySaved = adoptionService.createOneAdoption(adoptionEntity);
        URI uriAdoption = ucb
                .path("/api/v1/adoptions/{id}")
                .buildAndExpand(adoptionEntitySaved.getIdAdoption())
                .toUri();

        return ResponseEntity.created( uriAdoption ).body(adoptionEntitySaved);

    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AdoptionEntity> updatedAdoption (@RequestBody AdoptionEntity updatedAdoptionEntity, @PathVariable Long id) {
        return ResponseEntity.ok (

                adoptionService.getAdoptionById( id )
                        .map(adoptionEntity -> {

                            adoptionEntity.setUser( updatedAdoptionEntity.getUser() );
                            adoptionEntity.setPet( updatedAdoptionEntity.getPet() );
                            adoptionService.updateOneAdoption(adoptionEntity);

                            return ResponseEntity.ok(adoptionEntity);

                        }).orElseGet(() -> {

                            return ResponseEntity.notFound().build();

                        }).getBody()

        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAdoption (@PathVariable Long id) {
        adoptionService.deleteAdoptionById(id);

        return ResponseEntity.ok().body("Was delete species with id " + id);
    }

}
