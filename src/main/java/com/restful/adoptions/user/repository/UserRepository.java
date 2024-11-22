package com.restful.adoptions.user.repository;

import com.restful.adoptions.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //Optional<UserEntity> findEntityByUsername(String username );

}
