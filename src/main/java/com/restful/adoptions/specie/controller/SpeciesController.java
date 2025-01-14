package com.restful.adoptions.specie.controller;

import com.restful.adoptions.specie.model.SpeciesEntity;
import com.restful.adoptions.specie.service.SpeciesService;
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
@RequestMapping ("/api/v1/species")
@Tag(name = "Especies", description = "Controlador para gestionar especies de animales en adopción")
public class SpeciesController {

    @Autowired
    private SpeciesService speciesService;

    @GetMapping
    @Operation(summary = "Obtener todas las especies", description = "Devuelve una lista de todas las especies registradas en el sistema.")
    public ResponseEntity <List<SpeciesEntity>> getSpecies() {
        return ResponseEntity.ok( speciesService.getAllSpecies() );
    }

    @PostMapping
    @Operation(summary = "Crear una nueva especie", description = "Registra una nueva especie en el sistema y devuelve la entidad creada.")
    public ResponseEntity<SpeciesEntity> createSpecies(
            @RequestBody @Parameter(description = "Entidad de especie a crear") SpeciesEntity speciesEntity,
            UriComponentsBuilder ucb
    ) {

        SpeciesEntity speciesEntitySaved = speciesService.createOneSpecies(speciesEntity);
        URI uriSpecies = ucb
                .path("/api/v1/species/{id}")
                .buildAndExpand(speciesEntitySaved.getIdSpecies())
                .toUri();

        return ResponseEntity.created( uriSpecies ).body(speciesEntitySaved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una especie", description = "Modifica los datos de una especie existente identificada por su ID.")
    public ResponseEntity<?> updateSpecies(
            @RequestBody @Parameter(description = "Datos actualizados de la especie") SpeciesEntity updatedSpeciesEntity,
            @PathVariable @Parameter(description = "ID de la especie a actualizar") Long id
    ) {

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
    @Operation(summary = "Eliminar una especie", description = "Elimina una especie del sistema identificada por su ID.")
    public ResponseEntity<?> deleteSpecies(
            @PathVariable @Parameter(description = "ID de la especie a eliminar") Long id
    ) {

        speciesService.deleteOneSpeciesById(id);

        return ResponseEntity.ok().body("Was delete species with id " + id);
    }

}
