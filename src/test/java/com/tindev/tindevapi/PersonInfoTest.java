package com.tindev.tindevapi;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.personInfo.PersonInfoCreateDTO;
import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
import com.tindev.tindevapi.entities.PersonInfoEntity;
import com.tindev.tindevapi.repository.PersonInfoRepository;
import com.tindev.tindevapi.repository.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.service.LogService;
import com.tindev.tindevapi.service.PersonInfoService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PersonInfoTest {


    @Mock
    private PersonInfoRepository personInfoRepository;

    @InjectMocks
    private PersonInfoService personInfoService;

    @Mock
    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private LogService logService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePersonInfo() {
        PersonInfoEntity personInfoEntity = PersonInfoEntity.builder().build();
        PersonInfoCreateDTO personInfoCreateDTO = getPersonInfoCreate();
        when(objectMapper.convertValue(personInfoCreateDTO, PersonInfoEntity.class)).thenReturn(personInfoEntity);
        when(personInfoRepository.save(any(PersonInfoEntity.class))).thenReturn(personInfoEntity);
        personInfoService.createPersonInfo(personInfoCreateDTO);
        verify(personInfoRepository, times(1)).save(any(PersonInfoEntity.class));
    }

    @Test
    public void testEmailById() throws RegraDeNegocioException {
        PersonInfoEntity personIfoEntity = PersonInfoEntity.builder().build();
        when(personInfoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(personIfoEntity));
        PersonInfoDTO personInfoDTO = getPersonInfoDTO();
        personInfoService.listPersonInfo(1);
        assertEquals("emailteste@tindev.com.br", personInfoDTO.getEmail());
    }

    @Test
    public void testExceptionListPersonInfo() {
        assertThrows(RegraDeNegocioException.class, () -> {
            personInfoService.listPersonInfo(10000);
        });
    }

    @Test
    public void testDeletePersonInfo() throws RegraDeNegocioException {
        PersonInfoEntity personInfoEntity = PersonInfoEntity.builder().build();
        when(personInfoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(personInfoEntity));
        personInfoService.delete(1);
        verify(personInfoRepository, times(1)).deleteById(anyInt());
    }

    private PersonInfoDTO getPersonInfoDTO() {
        var personInfoDTO = new PersonInfoDTO();
        BeanUtils.copyProperties(getPersonInfoCreate(), personInfoDTO);
        personInfoDTO.setIdPersonInfo(1);
        return personInfoDTO;
    }

        private PersonInfoCreateDTO getPersonInfoCreate() {
          var personInfoCreateDTO = new PersonInfoCreateDTO();
          personInfoCreateDTO.setRealName("Teste Real Name");
          personInfoCreateDTO.setAge(20);
          personInfoCreateDTO.setEmail("emailteste@tindev.com.br");
          return personInfoCreateDTO;
        }
    }

