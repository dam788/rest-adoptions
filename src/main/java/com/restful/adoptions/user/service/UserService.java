package com.restful.adoptions.user.service;

import com.restful.adoptions.user.model.UserEntity;
import com.restful.adoptions.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public List <UserEntity> getAllUsers () {
        return userRepository.findAll();
    }

    public Optional <UserEntity> getUserById (Long id ) {
        return userRepository.findById( id );
    }

    public UserEntity createOneUser (UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity updateOneUser (UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity deleteOneUser (UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
