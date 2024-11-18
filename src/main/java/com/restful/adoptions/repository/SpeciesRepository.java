package com.restful.adoptions.repository;

import com.restful.adoptions.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends JpaRepository <Species, Long> {
}
