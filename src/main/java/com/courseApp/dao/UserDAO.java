package com.courseApp.dao;

import com.courseApp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * UserDAO interface for querying user data, register action and login action.
 *
 * Supported by spring boot repository.
 */
@Repository
public interface UserDAO extends MongoRepository<User, String> {

    /**
     * Return a User obj iff the encrypted password is correct.
     *
     * @param username username
     * @param encryptedPassword encrypted password
     * @return User Entity
     */
    User findByUsernameAndEncryptedPassword(String username, String encryptedPassword);

    /**
     * Return a User obj
     *
     * @param username username
     * @return User Entity
     */
    User findByUsername(String username);

    /**
     * Return true iff the username and password matches the record.
     *
     * @param username username
     * @param encryptedPassword encrypted password
     * @return User Entity
     */
    Boolean existsByUsernameAndEncryptedPassword(String username, String encryptedPassword);

    /**
     * Check if the username already exists in the DB.
     *
     * @return true iff the username is in the DB, otherwise, false
     */
    Boolean existsByUsername(String username);

}