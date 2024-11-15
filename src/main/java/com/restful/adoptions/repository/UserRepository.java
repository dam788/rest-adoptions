package com.restful.adoptions.repository;

import com.restful.adoptions.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
