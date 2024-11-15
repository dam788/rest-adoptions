package com.restful.adoptions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository <com.restful.adoptions.model.Species, Long> {
}
