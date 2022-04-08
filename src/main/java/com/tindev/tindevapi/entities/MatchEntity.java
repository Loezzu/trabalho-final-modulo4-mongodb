package com.tindev.tindevapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "MATCH_TINDEV_USER")
public class MatchEntity {

    @Id
    @Column(name = "ID_MATCH", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matchId;

    @Column(name = "USER_ID_FIRST", insertable = false, updatable = false)
    private Integer matchedUserFirst;

    @Column(name = "USERNAME_FIRST", updatable = false)
    private String nameFirst;

    @Column(name = "USER_ID_SECOND",insertable = false, updatable = false)
    private Integer matchedUserSecond;

    @Column(name = "USERNAME_SECOND", updatable = false)
    private String nameSecond;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID_FIRST")
    private UserEntity userEntityFirst;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID_SECOND")
    private UserEntity userEntitySecond;



}
