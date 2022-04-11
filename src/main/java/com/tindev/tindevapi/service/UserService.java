package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tindev.tindevapi.entities.AddressEntity;
import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
import com.tindev.tindevapi.dto.user.*;
import com.tindev.tindevapi.entities.RoleEntity;
import com.tindev.tindevapi.entities.UserEntity;
import com.tindev.tindevapi.enums.Roles;
import com.tindev.tindevapi.enums.TipoLog;
import com.tindev.tindevapi.repository.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.repository.AddressRepository;
import com.tindev.tindevapi.repository.PersonInfoRepository;
import com.tindev.tindevapi.repository.RoleRepository;
import com.tindev.tindevapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PersonInfoRepository personInfoRepository;
    private final RoleRepository roleRepository;
    private final ObjectMapper objectMapper;
    private final LogService logService;

    public Optional<UserEntity> findByUsername(String username) throws RegraDeNegocioException {
        return Optional.ofNullable(userRepository.findByUsername(username).orElseThrow(() -> new RegraDeNegocioException("User not found")));
    }

    public List<UserDTO> listUsers(Integer id) throws RegraDeNegocioException {
        if (id != null) {
            UserEntity user = userRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
            logService.logPost(TipoLog.USER,"User "+ user.getUsername() +" found");
            return userRepository.findById(id).stream().map(userEntity ->
                            objectMapper.convertValue(userEntity, UserDTO.class))
                    .collect(Collectors.toList());
        }
        logService.logPost(TipoLog.USER,"User list found");
        return userRepository.findAll().stream()
                .map(user -> objectMapper.convertValue(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTOCompleto getUserLoged() throws RegraDeNegocioException {
        UserEntity userLoged = userRepository.getById(getIdUserLoged());
        logService.logPost(TipoLog.USER,"User " +userLoged.getUsername()+ " found");
        return getUserComplete(userLoged);
    }

    public UserDTOWithoutPassword createUser(UserCreateDTO userCreateDTO, Roles role) throws Exception {
        var userEntity = new UserEntity();
        BeanUtils.copyProperties(userCreateDTO, userEntity, "password");
        userEntity.setAddress(addressRepository.findById(userEntity.getAddressId()).orElseThrow(() -> new RegraDeNegocioException("Address not found")));
        userEntity.setPersonInfoEntity(personInfoRepository.findById(userEntity.getPersoInfoId()).orElseThrow(() -> new RegraDeNegocioException("PersonInfo not found")));
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userCreateDTO.getPassword()));
        userEntity.setRole(roleRepository.findById(role.getRole()).orElseThrow(() -> new RegraDeNegocioException("Role not found!")));

        userRepository.save(userEntity);
        logService.logPost(TipoLog.USER,"User "+ userEntity.getUsername() +" created");
        return objectMapper.convertValue(userCreateDTO, UserDTOWithoutPassword.class);
    }

    public void updateUser(Integer id, UserUpdateDTO userUpdated) throws RegraDeNegocioException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("ID not found"));;
        userEntity.setGender(userUpdated.getGender());
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userUpdated.getPassword()));
        userEntity.setUsername(userUpdated.getUsername());
        userEntity.setProgLangs(userUpdated.getProgLangs());
        userEntity.setPref(userUpdated.getPref());
        logService.logPost(TipoLog.USER,"User "+userEntity.getUsername()+  " updated");
        userRepository.save(userEntity);
    }

    public void updateLogedUser(UserUpdateDTO userUpdated) throws RegraDeNegocioException {
        userRepository.findById(getIdUserLoged()).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
        UserEntity userEntity = userRepository.getById(getIdUserLoged());
        userEntity.setGender(userUpdated.getGender());
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userUpdated.getPassword()));
        userEntity.setUsername(userUpdated.getUsername());
        userEntity.setProgLangs(userUpdated.getProgLangs());
        userEntity.setPref(userUpdated.getPref());
        logService.logPost(TipoLog.USER,"User " +userEntity.getUsername()+ " logged updated");
        userRepository.save(userEntity);
    }

    public void deleteUser(Integer id) throws RegraDeNegocioException {
       UserEntity userEntity =  userRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
        logService.logPost(TipoLog.USER,"User " +userEntity.getUsername()+ " deleted");
        userRepository.deleteById(id);
    }

    public UserDTO getUserById(Integer id) throws RegraDeNegocioException {
       UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
        logService.logPost(TipoLog.USER,"User " +userEntity.getUsername()+ " found");
        return objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).convertValue((userRepository.findById(id)), UserDTO.class);
    }

    public List<UserDTOWithoutPassword> listLikesOfTheLogedUser() throws RegraDeNegocioException {
       UserEntity user = userRepository.findById(getIdUserLoged()).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
        logService.logPost(TipoLog.USER,"User " +user.getUsername()+ " found");
        return userRepository.listLikesById(getIdUserLoged()).stream()
                .map(userEntity -> objectMapper.convertValue(userEntity, UserDTOWithoutPassword.class)).toList();
    }

    public List<UserDTOWithoutPassword> listReceivedLikesOfTheLogedUser() throws RegraDeNegocioException {
        UserEntity user = userRepository.findById(getIdUserLoged()).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
        logService.logPost(TipoLog.USER,"User " +user.getUsername()+ " found");
        return userRepository.listReceivedLikesById(getIdUserLoged()).stream()
                .map(userEntity -> objectMapper.convertValue(userEntity, UserDTOWithoutPassword.class)).toList();
    }

    public List<UserDTOCompleto> listUserDTOComplete(Integer id) throws RegraDeNegocioException {
        if (id == null) {
            logService.logPost(TipoLog.USER, "List of users found");
            return new ArrayList<>(userRepository.findAll().stream().map(this::getUserComplete).toList());
        } else {
           UserEntity user =  userRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
            logService.logPost(TipoLog.USER,"User " +user.getUsername()+ " found");
            return new ArrayList<>(userRepository.findAllById(Collections.singleton(id)).stream().map(this::getUserComplete).toList());
        }
    }

    public List<UserDTOWithoutPassword> listMatchesOfTheLogedUser() throws RegraDeNegocioException {
        UserEntity user = userRepository.findById(getIdUserLoged()).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
        logService.logPost(TipoLog.USER,"User " +user.getUsername()+ " found");
        return userRepository.listMatchesByUserId(getIdUserLoged()).stream()
                .map(userEntity -> objectMapper.convertValue(userEntity, UserDTOWithoutPassword.class)).toList();
    }

    public void deleteUserLoged() throws RegraDeNegocioException {
        UserEntity user = userRepository.findById(getIdUserLoged()).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
        logService.logPost(TipoLog.USER,"User " +user.getUsername()+ " deleted");
        userRepository.deleteById(user.getUserId());
    }


    private UserDTOCompleto getUserComplete(UserEntity userEntity) {
        UserDTOCompleto userDTOCompleto = objectMapper.convertValue(userEntity, UserDTOCompleto.class);
        userDTOCompleto.setAddressDTO(objectMapper.convertValue(userEntity.getAddress(), AddressEntity.class));
        userDTOCompleto.setAddressDTO(objectMapper.convertValue(userEntity.getAddress(), AddressEntity.class));
        userDTOCompleto.setPersonInfoDTO(objectMapper.convertValue(userEntity.getPersonInfoEntity(), PersonInfoDTO.class));
        return userDTOCompleto;
    }

    public List<UserDTOWithoutPassword> listAvailableLogedUser() throws Exception {
        try {
            UserEntity userEntity = getLogedUser();
            List<UserEntity> availableUsers = new ArrayList<>();
            RoleEntity roleEntity = roleRepository.findByName("ROLE_ADMIN");
            for (UserEntity user : userRepository.findAll()) {
                if(userEntity.getUsername().equals(user.getUsername()) || user.getRole().equals(roleEntity)){
                    continue;
                }
                if(userEntity.getPref().isCompatible(user.getGender()) && user.getPref().isCompatible(userEntity.getGender())) {
                    availableUsers.add(user);
                }
            }
            logService.logPost(TipoLog.USER,"User " +userEntity.getUsername()+ " found");
            return availableUsers.stream().map(user -> objectMapper.convertValue(user, UserDTOWithoutPassword.class))
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new RegraDeNegocioException("User not found");
        }
    }

    public void changeRoleUserLoged(Roles role) throws RegraDeNegocioException {
        UserEntity userLoged = getLogedUser();
        RoleEntity userRole = roleRepository.findById(role.getRole()).orElseThrow(() -> new RegraDeNegocioException("Role not found!"));

        if(userLoged.getRole().equals(userRole)){
            throw new RegraDeNegocioException("Você ja tem esse plano");
        }else {
            logService.logPost(TipoLog.USER,"User " +userLoged.getUsername()+ " role changed");
            userLoged.setRole(userRole);
            userRepository.save(userLoged);
        }
    }



    protected Integer getIdUserLoged(){
        return Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    protected UserEntity getLogedUser() throws RegraDeNegocioException {
        Integer id = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return userRepository.getById(id);
    }

}




