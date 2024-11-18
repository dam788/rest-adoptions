package com.restful.adoptions.service;

import com.restful.adoptions.model.Location;
import com.restful.adoptions.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Optional<Location> getLocationById (Long id) {
        return locationRepository.findById(id);
    }

    public Location createOneLocation ( Location location ) {
        return locationRepository.save ( location );
    }

    public void updateOneLocation ( Location location) {
        locationRepository.save ( location );
    }

    public void deleteOneLocationsById ( Long id) {
        locationRepository.deleteById(id);
    }
}
