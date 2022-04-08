package com.tindev.tindevapi;


import com.tindev.tindevapi.dto.address.AddressDTO;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.service.AddressService;
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
public class AddressTest {

    @Autowired
    private AddressService addressService;

    @Test
    public void deveRetornarNomeDaRuaPorId() throws RegraDeNegocioException {
        List<AddressDTO> addressDTO = addressService.listAddress(1);

        assertEquals("rua admin", addressDTO.stream().findFirst().get().getStreet());
    }

    @Test
    public void deveRetornarExceptionNaBuscaPeloId(){

        assertThrows(RegraDeNegocioException.class, () -> {
            addressService.listAddress(10000);
        });

    }
}
