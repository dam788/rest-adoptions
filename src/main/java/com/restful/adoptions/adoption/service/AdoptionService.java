package com.restful.adoptions.adoption.service;

import com.restful.adoptions.adoption.model.Adoption;
import com.restful.adoptions.adoption.repository.AdoptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptionService {

    @Autowired
    AdoptionRepository adoptionRepository;



    public List<Adoption> getAllAdoptions() {
        return adoptionRepository.findAll();
    }

    public Optional<Adoption> getAdoptionById(Long id) {
        return adoptionRepository.findById( id );
    }

    public Adoption createOneAdoption(Adoption adoption) {
        return adoptionRepository.save(adoption);
    }

    public Adoption updateOneAdoption(Adoption adoption) {
        return adoptionRepository.save(adoption);
    }

    public void deleteAdoptionById(Long id) {
        adoptionRepository.deleteById(id);
    }

}
