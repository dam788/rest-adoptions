package com.restful.adoptions.adoption.controller;

import com.restful.adoptions.adoption.model.AdoptionEntity;
import com.restful.adoptions.adoption.service.AdoptionService;
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

@RestController
@RequestMapping("/api/v1/adoptions")
@Tag(name = "Adopciones", description = "Controlador para gestionar las adopciones de mascotas")
public class AdoptionController {

    @Autowired
    AdoptionService adoptionService;


    @GetMapping
    @Operation(summary = "Obtener todas las adopciones", description = "Devuelve una lista de todas las adopciones registradas en la base de datos")
    @ApiResponse(responseCode = "200", description = "Lista de adopciones obtenida exitosamente")
    public ResponseEntity<List <AdoptionEntity> > getAllAdoptions() {

        return ResponseEntity.ok( adoptionService.getAllAdoptions() );

    }

    @PostMapping
    @Operation(summary = "Crear una nueva adopción", description = "Permite registrar una nueva adopción de una mascota")
    @ApiResponse(responseCode = "201", description = "Adopción creada exitosamente")
    public ResponseEntity<AdoptionEntity> createAdoption(@RequestBody AdoptionEntity adoptionEntity, UriComponentsBuilder ucb) {

        AdoptionEntity adoptionEntitySaved = adoptionService.createOneAdoption(adoptionEntity);
        URI uriAdoption = ucb
                .path("/api/v1/adoptions/{id}")
                .buildAndExpand(adoptionEntitySaved.getIdAdoption())
                .toUri();

        return ResponseEntity.created( uriAdoption ).body(adoptionEntitySaved);

    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar adopción", description = "Actualiza la información de una adopción existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Adopción actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Adopción no encontrada")
    })
    public ResponseEntity<AdoptionEntity> updatedAdoption(
            @RequestBody AdoptionEntity updatedAdoptionEntity,
            @Parameter(description = "ID de la adopción a actualizar", example = "1") @PathVariable Long id
    ) {
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
    @Operation(summary = "Eliminar adopción", description = "Elimina una adopción registrada")
    @ApiResponse(responseCode = "200", description = "Adopción eliminada exitosamente")
    public ResponseEntity<String> deleteAdoption (@PathVariable Long id) {
        adoptionService.deleteAdoptionById(id);

        return ResponseEntity.ok().body("Was delete species with id " + id);
    }

}
