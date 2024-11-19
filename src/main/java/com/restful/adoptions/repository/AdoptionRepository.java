package com.restful.adoptions.repository;

import com.restful.adoptions.model.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionRepository extends JpaRepository <Adoption, Long> {
}
