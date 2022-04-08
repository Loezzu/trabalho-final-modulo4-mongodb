package com.tindev.tindevapi;


import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.service.PersonInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonInfoTest {

    @Autowired
    private PersonInfoService personInfoService;

    @Test
    public void deveRetornarEmailPorId() throws RegraDeNegocioException {
        List<PersonInfoDTO> personInfoDTO = personInfoService.listPersonInfo(1);

        assertEquals("admin@admin.com", personInfoDTO.stream().findFirst().get().getEmail());

    }

    @Test
    public void deveRetornarExceptionNaBuscaPeloId(){

        assertThrows(RegraDeNegocioException.class, () -> {
            personInfoService.listPersonInfo(10000);
        });

    }
}
