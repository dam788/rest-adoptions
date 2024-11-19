package com.restful.adoptions.location.controller;

import com.restful.adoptions.location.model.Location;
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
    public ResponseEntity<Optional<Location>> getLocation(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.getLocationById(id));
    }

    @PostMapping
    public ResponseEntity <URI> createlocation (@RequestBody Location location, UriComponentsBuilder ucb ) {

        Location locationSaved = locationService.createOneLocation(location);
        URI uriLocation = ucb
                .path("/api/v1/locations/{id}")
                .buildAndExpand(locationSaved.getIdLocation())
                .toUri();
        return ResponseEntity.created( uriLocation ).body(uriLocation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLocation (@RequestBody Location updatedLocation, @PathVariable Long id) {

        return ResponseEntity.ok(
                locationService.getLocationById(id)
                        .map(location -> {
                            location.setProvince(updatedLocation.getProvince());
                            location.setCity(updatedLocation.getCity());
                            location.setLat(updatedLocation.getLat());
                            location.setLon(updatedLocation.getLon());
                            locationService.updateOneLocation(location);

                            return ResponseEntity.ok(location);

                        }).orElseGet(() -> ResponseEntity.notFound().build())
        );
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<?> deleteLocation(@PathVariable Long id) {

        locationService.deleteOneLocationsById(id);

        return ResponseEntity.ok().body("was delete Location with id " + id);
    }


}
