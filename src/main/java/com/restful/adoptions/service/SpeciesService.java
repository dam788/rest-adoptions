package com.restful.adoptions.service;

import com.restful.adoptions.model.Species;
import com.restful.adoptions.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpeciesService {

    @Autowired
    private SpeciesRepository speciesRepository;

    public List <Species> getAllSpecies () {
        return speciesRepository.findAll();
    }

    public Optional<Species> getSpeciesById (Long id) {
        return speciesRepository.findById(id);
    }

    public Species createOneSpecies ( Species species ) {
        return speciesRepository.save( species );
    }

    public void updateOneSpecies (Species species ) {
        speciesRepository.save(species);
    }

    public void deleteOneSpeciesById (Long id ) {
        speciesRepository.deleteById(id);
    }

}