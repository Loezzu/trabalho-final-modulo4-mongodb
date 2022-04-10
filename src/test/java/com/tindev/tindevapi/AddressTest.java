package com.tindev.tindevapi;


import com.tindev.tindevapi.dto.address.AddressCreateDTO;
import com.tindev.tindevapi.dto.address.AddressDTO;
import com.tindev.tindevapi.entities.AddressEntity;
import com.tindev.tindevapi.repository.AddressRepository;
import com.tindev.tindevapi.repository.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.service.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressTest {

    @InjectMocks
    @Autowired
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

//    @Test
//    public void deveRetornarNomeDaRuaPorId() throws RegraDeNegocioException {
//
//        when(addressRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getAddressEntity()));
//
//        AddressDTO addressDTO = getAddressDTO();
//
//        addressService.listAddress(1);
//        assertEquals("rua admin", addressDTO.getStreet());
//    }


    @Test
    public void deveRetornarExceptionNaBuscaPeloId(){

        assertThrows(RegraDeNegocioException.class, () -> {
            addressService.listAddress(10000);
        });
    }


    @Test
    public void deveTestarDeletarEndereco() throws RegraDeNegocioException {
        when(addressRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getAddressEntity()));

        addressService.deleteAddress(1);
        verify(addressRepository, times(1)).deleteById(anyInt());
    }



    private AddressDTO getAddressDTO(){
        var addressDTO = new AddressDTO();
        BeanUtils.copyProperties(getAddressEntity(), AddressDTO.class);
        return addressDTO;
    }

    private AddressEntity getAddressEntity(){
        var addressEntity = AddressEntity.builder()
                .idAddress(1)
                .street("rua admin")
                .number(123)
                .city("São Paulo")
                .cep("01234567")
                .build();
        return addressEntity;
    }

    private AddressCreateDTO getAddressCreate(){
        AddressCreateDTO addressCreateDTO = new AddressCreateDTO();
        addressCreateDTO.setStreet("rua admin");
        addressCreateDTO.setNumber(123);
        addressCreateDTO.setCity("São Paulo");
        addressCreateDTO.setCep("01234567");
        return addressCreateDTO;
    }



}
