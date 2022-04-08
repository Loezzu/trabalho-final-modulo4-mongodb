package com.tindev.tindevapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.user.UserCreateDTO;
import com.tindev.tindevapi.entities.AddressEntity;
import com.tindev.tindevapi.entities.PersonInfoEntity;
import com.tindev.tindevapi.entities.RoleEntity;
import com.tindev.tindevapi.entities.UserEntity;
import com.tindev.tindevapi.enums.Gender;
import com.tindev.tindevapi.enums.Pref;
import com.tindev.tindevapi.enums.ProgLangs;
import com.tindev.tindevapi.enums.Roles;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.repository.AddressRepository;
import com.tindev.tindevapi.repository.PersonInfoRepository;
import com.tindev.tindevapi.repository.RoleRepository;
import com.tindev.tindevapi.repository.UserRepository;
import com.tindev.tindevapi.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PersonInfoRepository personInfoRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveSalvarUsuario() throws Exception {
        AddressEntity addressEntity = AddressEntity.builder().idAddress(1).build();
        PersonInfoEntity personInfoEntity = PersonInfoEntity.builder().idPersonInfo(1).build();
        RoleEntity roleEntity = RoleEntity.builder().roleId(1).build();
        UserEntity userEntity = UserEntity.builder().userId(1).AddressId(1).PersoInfoId(1).build();
        UserCreateDTO userCreateDTO = getUserCreate();

        when(objectMapper.convertValue(any(UserCreateDTO.class), eq(UserEntity.class))).thenReturn(userEntity);
        when(roleRepository.findById(anyInt())).thenReturn(Optional.ofNullable(roleEntity));
        when(addressRepository.findById(anyInt())).thenReturn(Optional.ofNullable(addressEntity));
        when(personInfoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(personInfoEntity));

        userService.createUser(userCreateDTO, Roles.FREE);

        verify(userRepository, times(1)).save(userEntity);
    }


    @Test
    public void deveTestarDelete() throws Exception {
        UserEntity user = UserEntity.builder().build();

        when(userRepository.findById(any(Integer.class)))
                .thenReturn(Optional.ofNullable(user));

        userService.deleteUser(10);

        verify(userRepository, times(1)).deleteById(anyInt());

    }

    @Test(expected = RegraDeNegocioException.class)
    public void lancarExceptionQuandoIDNaoEncontrado() throws RegraDeNegocioException {
        userService.deleteUser(20);
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
}
