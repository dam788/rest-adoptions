package com.restful.adoptions.pet.repository;

import com.restful.adoptions.pet.model.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository <PetEntity, Long> {

}
