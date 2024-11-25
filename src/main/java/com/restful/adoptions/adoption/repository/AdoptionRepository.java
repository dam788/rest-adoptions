package com.restful.adoptions.adoption.repository;

import com.restful.adoptions.adoption.model.AdoptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionRepository extends JpaRepository <AdoptionEntity, Long> {
}
