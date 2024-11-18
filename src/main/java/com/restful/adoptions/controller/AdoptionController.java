package com.restful.adoptions.controller;

import com.restful.adoptions.model.Adoption;
import com.restful.adoptions.service.AdoptionService;
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
    public ResponseEntity<List <Adoption> > getAllAdoptions() {

        return ResponseEntity.ok( adoptionService.getAllAdoptions() );

    }

    @PostMapping
    public ResponseEntity<Adoption> createAdoption(@RequestBody Adoption adoption, UriComponentsBuilder ucb) {

        Adoption adoptionSaved = adoptionService.createOneAdoption(adoption);
        URI uriAdoption = ucb
                .path("/api/v1/adoptions/{id}")
                .buildAndExpand(adoptionSaved.getIdAdoption())
                .toUri();

        return ResponseEntity.created( uriAdoption ).body(adoptionSaved);

    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Adoption> updatedAdoption (@RequestBody Adoption updatedAdoption, @PathVariable Long id) {
        return ResponseEntity.ok (

                adoptionService.getAdoptionById( id )
                        .map(adoption -> {

                            adoption.setUser( updatedAdoption.getUser() );
                            adoption.setPet( updatedAdoption.getPet() );
                            adoptionService.updateOneAdoption(adoption);

                            return ResponseEntity.ok(adoption);

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
