package com.restful.adoptions.location.repository;

import com.restful.adoptions.location.model.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepository extends JpaRepository <LocationEntity, Long> {
}
