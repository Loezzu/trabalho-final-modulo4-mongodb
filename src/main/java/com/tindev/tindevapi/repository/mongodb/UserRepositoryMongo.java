package com.tindev.tindevapi.repository.mongodb;

import com.tindev.tindevapi.entities.mongodb.UserEntityMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositoryMongo extends MongoRepository<UserEntityMongo, String> {
}
