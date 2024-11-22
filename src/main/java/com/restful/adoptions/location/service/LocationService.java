package com.restful.adoptions.location.service;

import com.restful.adoptions.location.model.LocationEntity;
import com.restful.adoptions.location.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Optional<LocationEntity> getLocationById (Long id) {
        return locationRepository.findById(id);
    }

    public LocationEntity createOneLocation (LocationEntity locationEntity) {
        return locationRepository.save (locationEntity);
    }

    public void updateOneLocation ( LocationEntity locationEntity) {
        locationRepository.save (locationEntity);
    }

    public void deleteOneLocationsById ( Long id) {
        locationRepository.deleteById(id);
    }
}
