package com.restful.adoptions.location.controller;

import com.restful.adoptions.location.model.LocationEntity;
import com.restful.adoptions.location.service.LocationService;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/locations")
@Tag(name = "Ubicaciones", description = "Controlador para la gestión de ubicaciones de las mascotas")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una ubicación por ID", description = "Devuelve una ubicación específica basada en su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ubicación encontrada"),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada")
    })
    public ResponseEntity<Optional<LocationEntity>> getLocation(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.getLocationById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva ubicación", description = "Agrega una nueva ubicación a la base de datos")
    @ApiResponse(responseCode = "201", description = "Ubicación creada exitosamente")
    public ResponseEntity <URI> createlocation (@RequestBody LocationEntity locationEntity, UriComponentsBuilder ucb ) {

        LocationEntity locationEntitySaved = locationService.createOneLocation(locationEntity);
        URI uriLocation = ucb
                .path("/api/v1/locations/{id}")
                .buildAndExpand(locationEntitySaved.getIdLocation())
                .toUri();
        return ResponseEntity.created( uriLocation ).body(uriLocation);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una ubicación", description = "Modifica la información de una ubicación existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ubicación actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada")
    })
    public ResponseEntity<?> updateLocation(
            @RequestBody LocationEntity updatedLocationEntity,
            @Parameter(description = "ID de la ubicación a actualizar", example = "1") @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                locationService.getLocationById(id)
                        .map(locationEntity -> {
                            locationEntity.setProvince(updatedLocationEntity.getProvince());
                            locationEntity.setCity(updatedLocationEntity.getCity());
                            locationEntity.setLat(updatedLocationEntity.getLat());
                            locationEntity.setLon(updatedLocationEntity.getLon());
                            locationService.updateOneLocation(locationEntity);

                            return ResponseEntity.ok(locationEntity);

                        }).orElseGet(() -> ResponseEntity.notFound().build())
        );
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar una ubicación", description = "Elimina una ubicación de la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ubicación eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada")
    })
    public ResponseEntity<?> deleteLocation(
            @Parameter(description = "ID de la ubicación a eliminar", example = "1") @PathVariable Long id
    ) {

        locationService.deleteOneLocationsById(id);

        return ResponseEntity.ok().body("was delete Location with id " + id);
    }


}
