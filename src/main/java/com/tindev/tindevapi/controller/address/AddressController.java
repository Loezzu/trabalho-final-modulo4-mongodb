package com.tindev.tindevapi.controller.address;

import com.tindev.tindevapi.dto.address.AddressCreateDTO;
import com.tindev.tindevapi.dto.address.AddressDTO;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.service.AddressService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/address")
@Validated
@Api(value = "2 - Address API", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"2 - Address API"})
public class AddressController implements AddressAPI {

    @Autowired
    private AddressService addressService;

    @GetMapping("/list-address")
    public ResponseEntity<List<AddressDTO>> listAddress(@RequestParam(required = false) Integer id) throws Exception{
        return ResponseEntity.ok(addressService.listAddress(id));
    }

    @PostMapping("/create")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressCreateDTO addressCreateDTO){
        return ResponseEntity.ok(addressService.createAddress(addressCreateDTO));
    }
    @PutMapping("/update")
    public ResponseEntity<AddressDTO> update(@RequestBody AddressCreateDTO addressCreateDTO, @RequestParam("id") Integer id) throws Exception{
        return ResponseEntity.ok(addressService.updateAddress(addressCreateDTO, id));
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> delete(@RequestParam("id") Integer id) throws Exception{
        addressService.deleteAddress(id);
        return new ResponseEntity<>("Address deleted", HttpStatus.ACCEPTED);
    }

    @GetMapping("/loged-user/get")
    public ResponseEntity<AddressDTO> getAddressByLogedUser() throws Exception{
        return ResponseEntity.ok(addressService.getLogedUserAddress());
    }

    @PutMapping("/loged-user/update")
    public ResponseEntity<AddressDTO> updateAddressByLogedUser(@Valid @RequestBody AddressCreateDTO addressCreateDTO) throws Exception{
        return ResponseEntity.ok(addressService.updateLogedUserAddress(addressCreateDTO));
    }

}
