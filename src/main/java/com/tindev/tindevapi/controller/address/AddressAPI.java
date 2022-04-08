package com.tindev.tindevapi.controller.address;

import com.tindev.tindevapi.dto.address.AddressCreateDTO;
import com.tindev.tindevapi.dto.address.AddressDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Api
@Validated
public interface AddressAPI {

    @ApiOperation(value = "Retorna uma lista de endereços / Retorna endereço por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de endereços / Retorna endereço por ID "),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<List<AddressDTO>> listAddress(@RequestParam(required = false) Integer id) throws Exception;

    @ApiOperation(value = "Adiciona um endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um endereço com um id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressCreateDTO addressCreateDTO);

    @ApiOperation(value = "Atualiza um endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um endereço atualizado pelo id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<AddressDTO> update(@RequestBody AddressCreateDTO addressCreateDTO, @RequestParam("id") Integer id) throws Exception;


    @ApiOperation(value = "Deleta um endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem de sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<String> delete(@RequestParam("id") Integer id) throws Exception;

    @ApiOperation(value = "Mostra o endereço do usuário logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o endereço do usuário logado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<AddressDTO> getAddressByLogedUser() throws Exception;

    @ApiOperation(value = "Atualiza o endereço do usuário logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o endereço atualizado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<AddressDTO> updateAddressByLogedUser(@Valid @RequestBody AddressCreateDTO addressCreateDTO) throws Exception;
}
