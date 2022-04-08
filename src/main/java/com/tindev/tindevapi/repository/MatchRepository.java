package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.entities.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Integer> {
    @Query("SELECT m FROM MATCH_TINDEV_USER m WHERE m.matchedUserFirst = ?1 OR m.matchedUserSecond= ?1")
    List<MatchEntity> findByMatchedUserFirstOrAndMatchedUserSecond(Integer userId);

    MatchEntity findByMatchedUserFirstAndMatchedUserSecond(Integer userFirst, Integer userSecond);

}
