package com.demo.userdatacollection.dao.service;

import com.demo.userdatacollection.dao.entity.UserEntity;
import com.demo.userdatacollection.dao.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * get one row of the user search by user_id column
     *
     * @param userId
     * @return
     */
    public UserEntity getUserByUserId(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    /**
     * insert a new user with details
     *
     * @param user
     * @return
     */
    public UserEntity insertNewUser(UserEntity user) {
        return userRepository.save(user);
    }

    /**
     * Update details of an existing user
     *
     * @param user
     * @return
     */
    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }

    /**
     * search user for by the provided email
     *
     * @param email
     * @return
     */
    public UserEntity getUserByEmail(String email) {
        return userRepository.searchByEmail(email);
    }
}
