package com.zapphireye.zapphireye.repository;

import com.zapphireye.zapphireye.model.database.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

}
