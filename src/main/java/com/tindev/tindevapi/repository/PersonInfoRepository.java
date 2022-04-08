package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.entities.PersonInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonInfoRepository extends JpaRepository<PersonInfoEntity, Integer> {

}
