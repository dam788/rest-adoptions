package com.restful.adoptions.repository;

import com.restful.adoptions.model.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository <Adoption, Long> {
}
