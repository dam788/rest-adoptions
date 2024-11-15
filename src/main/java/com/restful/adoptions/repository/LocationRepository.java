package com.restful.adoptions.repository;

import com.restful.adoptions.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository <Location, Long> {
}
