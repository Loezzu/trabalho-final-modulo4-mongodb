package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.like.LikeDTO;
import com.tindev.tindevapi.entities.LikeEntity;
import com.tindev.tindevapi.entities.UserEntity;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final ObjectMapper objectMapper;
    private final MatchService matchService;
    private final UserService userService;

    public List<LikeDTO> listAllLikes() {
        return likeRepository.findAll()
                .stream()
                .map(like ->  objectMapper.convertValue(like, LikeDTO.class))
                .collect(Collectors.toList());
    }

    public List<LikeDTO> listAllLikesByUser(Integer id) throws RegraDeNegocioException {
        userService.getUserById(id);
        return likeRepository.findAllByUserId(id)
                .stream()
                .map(like -> objectMapper.convertValue(like, LikeDTO.class))
                .collect(Collectors.toList());
    }

    public LikeDTO giveLike(Integer userId, Integer likedUserId) throws Exception {
        if (likeRepository.findByUserIdAndLikedUserId(userId, likedUserId) != null) {
            throw new RegraDeNegocioException("like already exists");
        }
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setUserId(userId);
        likeEntity.setUsernameUser(userService.getUserById(userId).getUsername());
        likeEntity.setLikedUserId(likedUserId);
        likeEntity.setUsernameLikedUser(userService.getUserById(likedUserId).getUsername());
        likeEntity.setUserEntity(objectMapper.convertValue(userService.getUserById(userId), UserEntity.class));
        likeEntity.setUserEntityLiked(objectMapper.convertValue(userService.getUserById(likedUserId), UserEntity.class));
        likeRepository.save(likeEntity);
        if (likeRepository.findByUserIdAndLikedUserId(userId, likedUserId) != null &&
                likeRepository.findByLikedUserIdAndUserId(userId, likedUserId) != null) {
            matchService.addMatch(userId, likedUserId);
        }
        return objectMapper.convertValue(likeEntity, LikeDTO.class);
    }

    public void deleteLike(Integer id) throws RegraDeNegocioException {
        likeRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
        likeRepository.deleteById(id);
    }

    public void deleteLikeByUserId(Integer id) throws RegraDeNegocioException {
        userService.getUserById(id);
        likeRepository.deleteAll(likeRepository.findAllByUserId(id));
    }

    public void deleteLikesByLogedUser() throws RegraDeNegocioException {
        deleteLikeByUserId(userService.getLogedUserId());
    }

    public LikeDTO giveLikeByLogedUser(Integer likedUserId) throws Exception {
        return giveLike(userService.getLogedUserId(), likedUserId);
    }
}



