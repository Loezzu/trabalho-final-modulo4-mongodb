package com.tindev.tindevapi.controller.personinfo;

import com.tindev.tindevapi.dto.personInfo.PersonInfoCreateDTO;
import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
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
public interface PersonInfoAPI {

    @ApiOperation(value = "Mostra uma lista de dados pessoais / Mostra os dados pessoais por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de dados pessoais / Mostra os dados pessoais por ID"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<List<PersonInfoDTO>> list(@RequestParam(required = false) Integer id) throws Exception;

    @ApiOperation(value = "Adiciona um dado pessoal")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um dado pessoal com um id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<PersonInfoDTO> create(@RequestBody PersonInfoCreateDTO persoInfoDTO) throws Exception;

    @ApiOperation(value = "Atualiza um dado pessoal")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um dado pessoal atualizado pelo id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<PersonInfoDTO> update(@RequestBody PersonInfoCreateDTO personInfoCreateDTO, @RequestParam("id") Integer id) throws Exception;


    @ApiOperation(value = "Deleta um dado pessoal")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem de sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<String> delete(@RequestParam("id") Integer id) throws Exception;

    @ApiOperation(value = "Mostra as informações pessoais do usuário logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna as informações pessoais do usuário logado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<PersonInfoDTO> findByLogedUser() throws Exception;

    @ApiOperation(value = "Atualiza as informações pessoais do usuário logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna as informações pessoais atualizadas"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<PersonInfoDTO> updateByLogedUser(@RequestBody @Valid PersonInfoCreateDTO personInfoCreateDTO) throws Exception;

}
