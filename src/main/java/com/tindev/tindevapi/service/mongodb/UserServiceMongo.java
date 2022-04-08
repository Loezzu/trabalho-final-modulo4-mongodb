package com.tindev.tindevapi.service.mongodb;

import com.tindev.tindevapi.entities.mongodb.UserEntityMongo;
import com.tindev.tindevapi.repository.mongodb.UserRepositoryMongo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceMongo {

    @Autowired
    private UserRepositoryMongo userRepositoryMongo;


//    public UserEntityMongo salvar(UserEntityMongo userCreate) {
//       return userRepositoryMongo.save(new UserEntityMongo(userCreate));
//    }
//
//    public List<UserEntityMongo> findAll() {
//        return userRepositoryMongo.findAll();
//    }


}
