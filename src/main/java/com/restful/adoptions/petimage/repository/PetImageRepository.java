package com.restful.adoptions.petimage.repository;

import com.restful.adoptions.petimage.model.PetImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetImageRepository extends JpaRepository <PetImage, Long> {
}
