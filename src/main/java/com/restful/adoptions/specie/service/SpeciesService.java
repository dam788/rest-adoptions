package com.restful.adoptions.specie.service;

import com.restful.adoptions.specie.model.SpeciesEntity;
import com.restful.adoptions.specie.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpeciesService {

    @Autowired
    private SpeciesRepository speciesRepository;



    public List <SpeciesEntity> getAllSpecies () {
        return speciesRepository.findAll();
    }

    public Optional<SpeciesEntity> getSpeciesById (Long id) {
        return speciesRepository.findById(id);
    }

    public SpeciesEntity createOneSpecies (SpeciesEntity speciesEntity) {
        return speciesRepository.save(speciesEntity);
    }

    public void updateOneSpecies (SpeciesEntity speciesEntity) {
        speciesRepository.save(speciesEntity);
    }

    public void deleteOneSpeciesById (Long id ) {
        speciesRepository.deleteById(id);
    }

}