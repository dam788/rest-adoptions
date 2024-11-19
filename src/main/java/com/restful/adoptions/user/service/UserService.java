package com.restful.adoptions.user.service;

import com.restful.adoptions.user.model.User;
import com.restful.adoptions.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public List <User> getAllUsers () {
        return userRepository.findAll();
    }

    public Optional <User> getUserById ( Long id ) {
        return userRepository.findById( id );
    }

    public User createOneUser ( User user ) {
        return userRepository.save( user );
    }

    public User updateOneUser ( User user ) {
        return userRepository.save( user );
    }

    public User updatePetsUser ( User user ) {
        return userRepository.save( user );
    }

    public User updateLocationUser ( User user ) {
        return userRepository.save( user );
    }

    public User deleteOneUser ( User user ) {
        return userRepository.save( user );
    }

    public User deletePetsUser ( User user ) {
        return userRepository.save( user );
    }

}
