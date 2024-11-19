package com.restful.adoptions.pet.service;

import com.restful.adoptions.pet.model.Pet;
import com.restful.adoptions.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;



    public List<Pet> getAllPets () {
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById (Long id ) {
        return petRepository.findById( id );
    }

    public Pet createOnePet ( Pet pet ) {
        return petRepository.save( pet );
    }

    public Pet updateOnePet ( Pet pet ) {
        return petRepository.save( pet );
    }

    public Pet deleteOnePet ( Pet pet ) {
        return petRepository.save( pet );
    }

    public Pet notAvailablePet ( Pet pet ) {
        return petRepository.save( pet );
    }
}
