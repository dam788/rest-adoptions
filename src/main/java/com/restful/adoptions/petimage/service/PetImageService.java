package com.restful.adoptions.petimage.service;

import com.restful.adoptions.petimage.model.PetImageEntity;
import com.restful.adoptions.petimage.repository.PetImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetImageService {

    @Autowired
    private PetImageRepository petImageRepository;



    public List<PetImageEntity> getAllPetImages () {
        return petImageRepository.findAll();
    };

    public PetImageEntity createOnePickImage (PetImageEntity image ) {
        return petImageRepository.save( image );
    }

    public void deletePetImageById ( Long id ) {
        petImageRepository.deleteById( id );
    }

}
