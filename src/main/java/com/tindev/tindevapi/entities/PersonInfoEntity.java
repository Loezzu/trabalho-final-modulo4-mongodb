package com.tindev.tindevapi.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "personinfo")
public class PersonInfoEntity {

    @Id
    @Column(name = "id_personinfo", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonInfo;

    @Column(name = "realname")
    private String realName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

}
