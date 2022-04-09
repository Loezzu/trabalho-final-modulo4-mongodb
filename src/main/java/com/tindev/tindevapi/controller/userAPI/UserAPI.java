package com.tindev.tindevapi.controller.userAPI;

import com.tindev.tindevapi.dto.user.*;
import com.tindev.tindevapi.enums.Roles;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Api
@Validated
public interface UserAPI {

    @ApiOperation(value = "Retorna uma lista de usuários / Mostra um usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de usuários / Mostra um usuário por ID"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<List<UserDTO>> listUser(@RequestParam(required = false) Integer id) throws Exception;

    @ApiOperation(value = "Retorna o usuário logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o usuário logado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<UserDTOCompleto> getLogedUser() throws Exception;

    @ApiOperation(value = "Adiciona um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um usuário com um id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<UserDTOWithoutPassword> postUser(@Valid @RequestBody UserCreateDTO userCreateDTO, @RequestParam Roles role) throws Exception;

    @ApiOperation(value = "Atualiza um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem de sucesso!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<String> updatedUser(@PathVariable("userId") Integer id,
                                         @Valid @RequestBody UserUpdateDTO userUpdateDTO) throws Exception;

    @ApiOperation(value = "Atualiza o usuário logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem de sucesso!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<String> updatedLogedUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) throws Exception;

    @ApiOperation(value = "Deleta um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem de sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<String> deleteUser(@PathVariable("userId") Integer id) throws Exception;

    @ApiOperation(value = "Deleta usuário logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem de sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<String> deleteLogedUser() throws Exception;

    @ApiOperation(value = "Mostra um usuário completo por ID / Mostra lista de usuários completos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um usuário com ID / Retorna lista de usuários completos"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<List<UserDTOCompleto>> listUserComplete(@RequestParam(value = "id", required = false) Integer id) throws Exception;

    @ApiOperation(value = "Mostra os likes dados do usuário logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os likes dados do usuário logado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<List<UserDTOWithoutPassword>> listLikesLogedUser() throws Exception;

    @ApiOperation(value = "Mostra os likes recebidos do usuário logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os likes recebidos do usuário logado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<List<UserDTOWithoutPassword>> listReceivedLikesLogedUser() throws Exception ;

    @ApiOperation(value = "Mostra os matches do usuário logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os matches do usuário logado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<List<UserDTOWithoutPassword>> listMatchesLogedUser() throws Exception;

    @ApiOperation(value = "Mostra os usuários disponíveis do usuário logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os usuários disponíveis do usuário logado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<List<UserDTOWithoutPassword>> listAvailableUsersByLogedUser() throws Exception;

    @ApiOperation(value = "Trocar de plano")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem de sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<String> changeRoleLogedUser(@RequestParam Roles role) throws Exception;

}
