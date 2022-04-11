package com.tindev.tindevapi;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.like.LikeDTO;
import com.tindev.tindevapi.dto.user.UserDTO;
import com.tindev.tindevapi.entities.LikeEntity;
import com.tindev.tindevapi.entities.UserEntity;
import com.tindev.tindevapi.repository.LikeRepository;
import com.tindev.tindevapi.repository.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.service.LikeService;
import com.tindev.tindevapi.service.LogService;
import com.tindev.tindevapi.service.MatchService;
import com.tindev.tindevapi.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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
    private LikeRepository likeRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private UserService userService;

    @Mock
    private LogService logService;

    @InjectMocks
    private LikeService likeService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGiveLike() throws Exception {
        LikeEntity likeEntity = getLikeCreate();

        UserDTO userDTO = new UserDTO();

        when(userService.getUserById(any())).thenReturn(userDTO);
        when(likeRepository.save(any(LikeEntity.class))).thenReturn(likeEntity);

        likeService.giveLike(likeEntity.getUserId(), likeEntity.getLikedUserId());

        verify(likeRepository).save(any(LikeEntity.class));
    }

    @Test
    public void testDeleteLike() throws Exception {
        LikeEntity likeEntity = getLikeCreate();

        when(likeRepository.findById(anyInt())).thenReturn(Optional.of(likeEntity));

        likeService.deleteLike(likeEntity.getLikeId());

        verify(likeRepository).deleteById(likeEntity.getLikeId());
    }

    @Test
    public void testDeleteLikeNotFound() {
        when(likeRepository.findById(anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(RegraDeNegocioException.class, () -> {
            likeService.deleteLike(1);
        });
    }

    @Test
    public void testDeleteLikeByUserId() throws Exception {
        UserEntity userEntity = new UserEntity();

        when(likeRepository.findAllByUserId(anyInt())).thenReturn(userEntity.getLikes());

        likeService.deleteLikeByUserId(getLikeCreate().getUserId());

        verify(likeRepository).deleteAll(userEntity.getLikes());
    }

    private LikeEntity getLikeCreate() {
        return LikeEntity.builder()
                .likeId(1)
                .userId(1)
                .usernameUser("usernameUser")
                .likedUserId(2)
                .usernameLikedUser("usernameLikedUser")
                .build();

    }

}
