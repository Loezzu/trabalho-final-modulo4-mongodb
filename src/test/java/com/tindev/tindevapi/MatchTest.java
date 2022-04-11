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
import com.tindev.tindevapi.repository.exceptions.RegraDeNegocioException;
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
    public void deveDarMatch() throws Exception {

        UserDTO userDTO = new UserDTO(1);
        userDTO.setProgLangs(ProgLangs.JAVA);

        UserDTO userDTO2 = new UserDTO(2);
        userDTO2.setProgLangs(ProgLangs.JAVA);

        when(matchRepository.findByMatchedUserFirstAndMatchedUserSecond(any(),any())).thenReturn(null);
        when(userService.getUserById(1)).thenReturn(userDTO);
        when(userService.getUserById(2)).thenReturn(userDTO2);

        matchService.addMatch(1, 2);

        verify(matchRepository, times(1)).save(any(MatchEntity.class));

    }


    @Test(expected = RegraDeNegocioException.class)
    public void lancarExcecaoSeOMatchJaExiste() throws Exception {

        MatchEntity match = new MatchEntity(1,1,"teste",2,"teste2",new UserEntity(),new UserEntity());

        when(matchRepository.findByMatchedUserFirstAndMatchedUserSecond(any(),any())).thenReturn(match);

        matchService.addMatch(1,2);
    }

    @Test
    public void testarChamadaDeleteMatch() throws Exception {

        MatchEntity match = new MatchEntity();

        when(matchRepository.findById(anyInt())).thenReturn(Optional.of(match));
        matchService.deleteMatch(10);

        verify(matchRepository, times(1)).deleteById(10);

    }


}
