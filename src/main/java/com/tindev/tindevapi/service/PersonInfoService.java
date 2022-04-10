package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.personInfo.PersonInfoCreateDTO;
import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
import com.tindev.tindevapi.entities.PersonInfoEntity;
import com.tindev.tindevapi.enums.TipoLog;
import com.tindev.tindevapi.repository.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.repository.PersonInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
    private final LogService logService;

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
//            PersonInfoEntity personInfoEntity = objectMapper.convertValue(personInfoCreateDTO, PersonInfoEntity.class);
//            PersonInfoEntity savedPersonInfoEntity = personInfoRepository.save(personInfoEntity);
            var personInfoEntity = new PersonInfoEntity();
            BeanUtils.copyProperties(personInfoCreateDTO, personInfoEntity);
            personInfoRepository.save(personInfoEntity);
        logService.logPost(TipoLog.PERSONINFO, "PersonInfo "+  personInfoEntity.getIdPersonInfo() + " created");
            return objectMapper.convertValue(personInfoEntity, PersonInfoDTO.class);
        }

    public PersonInfoDTO updatePersonInfo(PersonInfoCreateDTO personInfoCreateDTO, Integer idPerson) throws RegraDeNegocioException {
        personInfoRepository.findById(idPerson).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
        PersonInfoEntity personInfoEntity = objectMapper.convertValue(
                (personInfoRepository.findById(idPerson)), PersonInfoEntity.class);
            personInfoEntity.setIdPersonInfo(idPerson);
            personInfoEntity.setAge(personInfoCreateDTO.getAge());
            personInfoEntity.setEmail(personInfoCreateDTO.getEmail());
            personInfoEntity.setRealName(personInfoCreateDTO.getRealName());
        logService.logPost(TipoLog.PERSONINFO, "PersonInfo "+  personInfoEntity.getIdPersonInfo() + " updated");
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
        logService.logPost(TipoLog.PERSONINFO, "PersonInfo logedUser"+  personInfo.getIdPersonInfo() + " updated");
        return objectMapper.convertValue(personInfoRepository.save(personInfo), PersonInfoDTO.class);
    }

}
