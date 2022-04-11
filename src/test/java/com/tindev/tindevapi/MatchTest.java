package com.tindev.tindevapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.user.UserCreateDTO;
import com.tindev.tindevapi.dto.user.UserDTO;
import com.tindev.tindevapi.entities.MatchEntity;
import com.tindev.tindevapi.entities.UserEntity;
import com.tindev.tindevapi.enums.Gender;
import com.tindev.tindevapi.enums.Pref;
import com.tindev.tindevapi.enums.ProgLangs;
import com.tindev.tindevapi.repository.MatchRepository;
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

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MatchTest {

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private UserService userService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private LogService logService;

    @InjectMocks
    private MatchService matchService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void addMatchTest() throws Exception {

        UserEntity userEntity = new UserEntity();
        UserEntity userEntity2 = new UserEntity();
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setUserEntityFirst(userEntity);
        matchEntity.setUserEntitySecond(userEntity2);
        matchEntity.setMatchId(1);
        matchEntity.setNameFirst("");
        matchEntity.setNameSecond("");
        matchEntity.setMatchedUserFirst(userEntity.getUserId());
        matchEntity.setMatchedUserSecond(userEntity2.getUserId());

        UserDTO userDTO = new UserDTO();

        userDTO.setProgLangs(ProgLangs.JAVA);
        when(userService.getUserById(anyInt())).thenReturn(userDTO);
        when(matchRepository.save(any(MatchEntity.class))).thenReturn(matchEntity);

        matchService.addMatch(1,2);

        verify(matchRepository).save(matchEntity);


    }


    private UserCreateDTO getUserCreate(){
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setPref(Pref.BOTH);
        userCreateDTO.setGender(Gender.FEMALE);
        userCreateDTO.setUsername("");
        userCreateDTO.setPassword("123");
        userCreateDTO.setAddressId(1);
        userCreateDTO.setPersoInfoId(1);
        userCreateDTO.setProgLangs(ProgLangs.JAVA);
        return userCreateDTO;
    }

    private UserCreateDTO getUserCreate2(){
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setPref(Pref.BOTH);
        userCreateDTO.setGender(Gender.FEMALE);
        userCreateDTO.setUsername("");
        userCreateDTO.setPassword("123");
        userCreateDTO.setAddressId(2);
        userCreateDTO.setPersoInfoId(2);
        userCreateDTO.setProgLangs(ProgLangs.JAVA);
        return userCreateDTO;
    }


}
