package com.tindev.tindevapi.dto.like;

import com.tindev.tindevapi.entities.UserEntity;
import lombok.Data;

@Data
public class LikeDTO {
    private Integer likeId;
    private Integer userId;
    private String usernameUser;
    private Integer likedUserId;
    private String usernameLikedUser;

}
