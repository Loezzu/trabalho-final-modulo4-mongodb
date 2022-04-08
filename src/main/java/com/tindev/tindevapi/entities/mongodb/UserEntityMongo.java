package com.tindev.tindevapi.entities.mongodb;


import com.tindev.tindevapi.enums.Gender;
import com.tindev.tindevapi.enums.Pref;
import com.tindev.tindevapi.enums.ProgLangs;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Document(collection = "tindev_user")
@Getter
@Setter
public class UserEntityMongo {

    @Id
    private String id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private ProgLangs progLangs;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Pref pref;


    public UserEntityMongo(String username, String password, ProgLangs progLangs, Gender gender, Pref pref) {
        this.username = username;
        this.password = password;
        this.progLangs = progLangs;
        this.gender = gender;
        this.pref = pref;
    }
}
