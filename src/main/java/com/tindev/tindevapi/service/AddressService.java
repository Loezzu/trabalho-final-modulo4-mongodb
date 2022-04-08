package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.address.AddressCreateDTO;
import com.tindev.tindevapi.dto.address.AddressDTO;
import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
import com.tindev.tindevapi.entities.AddressEntity;
import com.tindev.tindevapi.entities.PersonInfoEntity;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    public List<AddressDTO> listAddress(Integer id) throws RegraDeNegocioException {
        if(id != null){
            addressRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
            return addressRepository.findById(id)
                    .stream().map(
                            addressEntity -> objectMapper.convertValue(
                                    addressEntity, AddressDTO.class))
                    .collect(Collectors.toList());
        }
        return addressRepository.findAll()
                .stream()
                .map(address -> objectMapper.convertValue(address, AddressDTO.class))
                .collect(Collectors.toList());
    }

    public AddressDTO createAddress(AddressCreateDTO addressCreateDTO) {
        AddressEntity addressEntity = objectMapper.convertValue(addressCreateDTO, AddressEntity.class);
        AddressEntity savedAddressEntity = addressRepository.save(addressEntity);

        return objectMapper.convertValue(savedAddressEntity, AddressDTO.class);
    }

    public AddressDTO updateAddress(AddressCreateDTO addressCreateDTO, Integer idAddress) throws RegraDeNegocioException{
        addressRepository.findById(idAddress).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
        AddressEntity addressEntity = objectMapper.convertValue(
               (addressRepository.findById(idAddress)), AddressEntity.class);
        addressEntity.setIdAddress(idAddress);
        addressEntity.setStreet(addressCreateDTO.getStreet());
        addressEntity.setNumber(addressCreateDTO.getNumber());
        addressEntity.setCity(addressCreateDTO.getCity());
        addressEntity.setCep(addressCreateDTO.getCep());
        return objectMapper.convertValue((addressRepository.save(addressEntity)), AddressDTO.class);
    }

    public void deleteAddress(Integer id) throws RegraDeNegocioException {
        addressRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("ID not found"));
        addressRepository.deleteById(id);
    }

    public AddressDTO getLogedUserAddress() throws RegraDeNegocioException {
        AddressEntity address = userService.getLogedUser().getAddress();
        return objectMapper.convertValue(address, AddressDTO.class);
    }

    public AddressDTO updateLogedUserAddress(AddressCreateDTO addressCreateDTO) throws RegraDeNegocioException {
        AddressEntity address = userService.getLogedUser().getAddress();
        address.setStreet(addressCreateDTO.getStreet());
        address.setNumber(addressCreateDTO.getNumber());
        address.setCity(addressCreateDTO.getCity());
        address.setCep(addressCreateDTO.getCep());
        return objectMapper.convertValue(addressRepository.save(address), AddressDTO.class);
    }
}
