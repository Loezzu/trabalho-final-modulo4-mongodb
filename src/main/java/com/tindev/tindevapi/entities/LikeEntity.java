package com.tindev.tindevapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "like_tindev_user")
public class LikeEntity {

    @Id
    @Column(name = "id_like", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer likeId;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;

    @Column(name = "USERNAME_USER", updatable = false)
    private String usernameUser;

    @Column(name = "liked_user_id", insertable = false, updatable = false)
    private Integer likedUserId;

    @Column(name = "USERNAME_LIKED", updatable = false)
    private String usernameLikedUser;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "liked_user_id")
    private UserEntity userEntityLiked;

}
