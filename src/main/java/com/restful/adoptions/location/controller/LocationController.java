package com.restful.adoptions.location.controller;

import com.restful.adoptions.location.model.LocationEntity;
import com.restful.adoptions.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LocationEntity>> getLocation(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.getLocationById(id));
    }

    @PostMapping
    public ResponseEntity <URI> createlocation (@RequestBody LocationEntity locationEntity, UriComponentsBuilder ucb ) {

        LocationEntity locationEntitySaved = locationService.createOneLocation(locationEntity);
        URI uriLocation = ucb
                .path("/api/v1/locations/{id}")
                .buildAndExpand(locationEntitySaved.getIdLocation())
                .toUri();
        return ResponseEntity.created( uriLocation ).body(uriLocation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLocation (@RequestBody LocationEntity updatedLocationEntity, @PathVariable Long id) {

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
    public  ResponseEntity<?> deleteLocation(@PathVariable Long id) {

        locationService.deleteOneLocationsById(id);

        return ResponseEntity.ok().body("was delete Location with id " + id);
    }


}
