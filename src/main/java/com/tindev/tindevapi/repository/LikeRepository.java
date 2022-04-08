package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.entities.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
    LikeEntity findByUserIdAndLikedUserId(Integer userId, Integer likedUserId);
    LikeEntity findByLikedUserIdAndUserId(Integer userId, Integer likedUserId);
    List<LikeEntity> findAllByUserId(Integer id);

}
