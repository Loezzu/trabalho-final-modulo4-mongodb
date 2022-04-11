package com.tindev.tindevapi;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.like.LikeDTO;
import com.tindev.tindevapi.dto.user.UserDTO;
import com.tindev.tindevapi.entities.LikeEntity;
import com.tindev.tindevapi.entities.UserEntity;
import com.tindev.tindevapi.repository.LikeRepository;
import com.tindev.tindevapi.service.LikeService;
import com.tindev.tindevapi.service.LogService;
import com.tindev.tindevapi.service.MatchService;
import com.tindev.tindevapi.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LikeTest {

    @Mock
    private  LikeRepository likeRepository;

    @Mock
    private  ObjectMapper objectMapper;

    @Mock
    private  MatchService matchService;

    @Mock
    private  UserService userService;

    @Mock
    private LogService logService;

    @InjectMocks
    private LikeService likeService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testLike() throws Exception {
        LikeEntity likeEntity = LikeEntity.builder()
                .likeId(1)
                .userId(1)
                .usernameUser("usernameUser")
                .likedUserId(2)
                .usernameLikedUser("usernameLikedUser")
                .build();
        LikeDTO likeDTO = getLikeCreate();

        UserDTO userDTO = new UserDTO();

        when(userService.getUserById(any())).thenReturn(userDTO);
        when(likeRepository.save(any(LikeEntity.class))).thenReturn(likeEntity);

        likeService.giveLike(likeEntity.getUserId(), likeEntity.getLikedUserId());

        verify(likeRepository).save(any(LikeEntity.class));
    }

    private LikeDTO getLikeCreate() {
        return LikeDTO.builder()
                .likeId(1)
                .userId(1)
                .usernameUser("usernameUser")
                .likedUserId(2)
                .usernameLikedUser("usernameLikedUser")
                .build();

    }

}
