package com.restful.adoptions.service;

import com.restful.adoptions.model.PetImage;
import com.restful.adoptions.repository.PetImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetImageService {

    @Autowired
    private PetImageRepository petImageRepository;



    public List<PetImage> getAllPetImages () {
        return petImageRepository.findAll();
    };

    public PetImage createOnePickImage (PetImage image) {
        return petImageRepository.save( image );
    }

    public void deleteOnePetImage (Long id) {
        petImageRepository.deleteById( id );
    }

}
