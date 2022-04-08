package com.tindev.tindevapi.controller.mongodb;


import com.tindev.tindevapi.entities.mongodb.UserEntityMongo;
import com.tindev.tindevapi.repository.mongodb.UserRepositoryMongo;
import com.tindev.tindevapi.service.mongodb.UserServiceMongo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongodb/user")

public class UserMongoController {


    @Autowired
    private UserRepositoryMongo userRepositoryMongo;


//    @GetMapping("/findAll")
//    public List<UserEntityMongo> findAll() {
//
//    }


    @PostMapping("/save")
    public ResponseEntity<UserEntityMongo> save(@RequestBody UserEntityMongo userEntityMongo) {
        try {
            UserEntityMongo _userEntityMongo = userRepositoryMongo.save(new UserEntityMongo(userEntityMongo.getUsername(), userEntityMongo.getPassword(), userEntityMongo.getProgLangs(), userEntityMongo.getGender(), userEntityMongo.getPref()));
            return new ResponseEntity<>(_userEntityMongo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @GetMapping("/list")
//    public List<UserEntityMongo> list() {
//        return userServiceMongo.findAll();
//    }
//
//    @PostMapping("/save")
//    public UserEntityMongo save(@RequestBody UserEntityMongo user) {
//        return userServiceMongo.salvar(user);
//    }

}
