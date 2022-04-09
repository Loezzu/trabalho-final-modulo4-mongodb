package com.tindev.tindevapi.controller.personinfo;

import com.tindev.tindevapi.dto.personInfo.PersonInfoCreateDTO;
import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
import com.tindev.tindevapi.service.PersonInfoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/personinfo")
@RequiredArgsConstructor
@Api(value = "1 - PersonInfo API", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"1 - PersonInfo API"})
public class PersonInfoController implements PersonInfoAPI{

    private final PersonInfoService persoInfoService;

    @GetMapping("/list-person-info")
    public ResponseEntity<List<PersonInfoDTO>> list(@RequestParam(required = false) Integer id) throws Exception {
        return ResponseEntity.ok(persoInfoService.listPersonInfo(id));
    }

    @PostMapping("/create")
    public ResponseEntity<PersonInfoDTO> create(@RequestBody PersonInfoCreateDTO persoInfoDTO) {
        return ResponseEntity.ok(persoInfoService.createPersonInfo(persoInfoDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<PersonInfoDTO> update(@RequestBody PersonInfoCreateDTO personInfoCreateDTO, @RequestParam("id") Integer id) throws Exception{
        return ResponseEntity.ok(persoInfoService.updatePersonInfo(personInfoCreateDTO, id));
    }

    @DeleteMapping("/deleteUser-person-info-by-id")
    public ResponseEntity<String> delete(@RequestParam("id") Integer id) throws Exception{
        persoInfoService.delete(id);
        return new ResponseEntity<>("PersoInfo deleted", HttpStatus.ACCEPTED);
    }

    @GetMapping("/loged-user/get")
    public ResponseEntity<PersonInfoDTO> findByLogedUser() throws Exception {
        return ResponseEntity.ok(persoInfoService.getLogedUserPersonInfo());
    }

    @PutMapping("/loged-user/update")
    public ResponseEntity<PersonInfoDTO> updateByLogedUser(@RequestBody @Valid PersonInfoCreateDTO personInfoCreateDTO) throws Exception {
        return ResponseEntity.ok(persoInfoService.updateLogedUserPersonInfo(personInfoCreateDTO));
    }



}
