package com.restful.adoptions.adoption.service;

import com.restful.adoptions.adoption.model.AdoptionEntity;
import com.restful.adoptions.adoption.repository.AdoptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptionService {

    @Autowired
    AdoptionRepository adoptionRepository;



    public List<AdoptionEntity> getAllAdoptions() {
        return adoptionRepository.findAll();
    }

    public Optional<AdoptionEntity> getAdoptionById(Long id) {
        return adoptionRepository.findById( id );
    }

    public AdoptionEntity createOneAdoption(AdoptionEntity adoptionEntity) {
        return adoptionRepository.save(adoptionEntity);
    }

    public void updateOneAdoption(AdoptionEntity adoptionEntity) {
        adoptionRepository.save(adoptionEntity);
    }

    public void deleteAdoptionById(Long id) {
        adoptionRepository.deleteById(id);
    }

}
