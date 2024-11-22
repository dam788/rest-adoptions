package com.restful.adoptions.pet.service;

import com.restful.adoptions.pet.model.PetEntity;
import com.restful.adoptions.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;



    public List<PetEntity> getAllPets () {
        return petRepository.findAll();
    }

    public Optional<PetEntity> getPetById (Long id ) {
        return petRepository.findById( id );
    }

    public PetEntity createOnePet (PetEntity petEntity) {
        return petRepository.save(petEntity);
    }

    public PetEntity updateOnePet (PetEntity petEntity) {
        return petRepository.save(petEntity);
    }

    public PetEntity deleteOnePet (PetEntity petEntity) {
        return petRepository.save(petEntity);
    }

    public PetEntity notAvailablePet (PetEntity petEntity) {
        return petRepository.save(petEntity);
    }
}
