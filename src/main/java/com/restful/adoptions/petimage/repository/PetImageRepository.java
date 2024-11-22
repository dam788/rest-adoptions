package com.restful.adoptions.petimage.repository;

import com.restful.adoptions.petimage.model.PetImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetImageRepository extends JpaRepository <PetImageEntity, Long> {
}
