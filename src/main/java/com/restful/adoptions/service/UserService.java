package com.restful.adoptions.service;

import com.restful.adoptions.model.User;
import com.restful.adoptions.repository.UserRepository;
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
        return userRepository.findById(id);
    }

    public User createOneUser ( User createdUser ) {
        return userRepository.save( createdUser );
    }

    public User updateOneUser ( User updatedUser ) {
        return userRepository.save( updatedUser );
    }

    public User deleteOneUser ( User removedUser ) {
        return userRepository.save( removedUser );
    }

}
