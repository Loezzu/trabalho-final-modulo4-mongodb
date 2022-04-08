package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.personInfo.PersonInfoCreateDTO;
import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
import com.tindev.tindevapi.entities.PersonInfoEntity;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.repository.PersonInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonInfoService {

    private final PersonInfoRepository personInfoRepository;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    public List<PersonInfoDTO> listPersonInfo(Integer id) throws RegraDeNegocioException {
        if(id != null) {
            personInfoRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
            return personInfoRepository.findById(id)
                    .stream()
                    .map(persoInfo -> objectMapper.convertValue(persoInfo, PersonInfoDTO.class))
                    .collect(Collectors.toList());
        }
        return personInfoRepository.findAll()
                .stream()
                .map(persoInfo -> objectMapper.convertValue(persoInfo, PersonInfoDTO.class))
                .collect(Collectors.toList());
    }

    public PersonInfoDTO createPersonInfo(PersonInfoCreateDTO personInfoCreateDTO) {
            PersonInfoEntity personInfoEntity = objectMapper.convertValue(personInfoCreateDTO, PersonInfoEntity.class);
            PersonInfoEntity savedPersonInfoEntity = personInfoRepository.save(personInfoEntity);
            return objectMapper.convertValue(savedPersonInfoEntity, PersonInfoDTO.class);
        }

    public PersonInfoDTO updatePersonInfo(PersonInfoCreateDTO personInfoCreateDTO, Integer idPerson) throws RegraDeNegocioException {
        personInfoRepository.findById(idPerson).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
        PersonInfoEntity personInfoEntity = objectMapper.convertValue(
                (personInfoRepository.findById(idPerson)), PersonInfoEntity.class);
            personInfoEntity.setIdPersonInfo(idPerson);
            personInfoEntity.setAge(personInfoCreateDTO.getAge());
            personInfoEntity.setEmail(personInfoCreateDTO.getEmail());
            personInfoEntity.setRealName(personInfoCreateDTO.getRealName());
        return  objectMapper.convertValue((personInfoRepository.save(personInfoEntity)), PersonInfoDTO.class);
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        personInfoRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
        personInfoRepository.deleteById(id);
    }

    public PersonInfoDTO getLogedUserPersonInfo() throws RegraDeNegocioException {
        PersonInfoEntity personInfo = userService.getLogedUser().getPersonInfoEntity();
        return objectMapper.convertValue(personInfo, PersonInfoDTO.class);
    }

    public PersonInfoDTO updateLogedUserPersonInfo(PersonInfoCreateDTO personInfoCreateDTO) throws RegraDeNegocioException {
        PersonInfoEntity personInfo = userService.getLogedUser().getPersonInfoEntity();
        personInfo.setAge(personInfoCreateDTO.getAge());
        personInfo.setEmail(personInfoCreateDTO.getEmail());
        personInfo.setRealName(personInfoCreateDTO.getRealName());
        return objectMapper.convertValue(personInfoRepository.save(personInfo), PersonInfoDTO.class);
    }

}
