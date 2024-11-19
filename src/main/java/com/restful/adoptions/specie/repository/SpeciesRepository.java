package com.restful.adoptions.specie.repository;


import com.restful.adoptions.specie.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends JpaRepository <Species, Long> {
}
