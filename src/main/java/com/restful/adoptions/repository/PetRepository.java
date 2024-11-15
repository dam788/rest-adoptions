package com.restful.adoptions.repository;

import com.restful.adoptions.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository <Pet, Long> {

}
