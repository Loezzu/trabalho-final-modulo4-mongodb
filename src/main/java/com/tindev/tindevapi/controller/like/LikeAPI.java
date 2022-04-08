package com.tindev.tindevapi.controller.like;

import com.tindev.tindevapi.dto.like.LikeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api
@Validated
public interface LikeAPI {

    @ApiOperation(value = "Retorna uma lista de todos likes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de todos likes"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<List<LikeDTO>> listLikes();

    @ApiOperation(value = "Retorna uma lista likes de um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista likes de um usuário"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<List<LikeDTO>> listLikeOfTheUser(@PathVariable("userId")Integer id) throws Exception;

    @ApiOperation(value = "Dar um like")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Dar um like"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<LikeDTO> darLike(@PathVariable("userId") Integer userId, @PathVariable("likedUserId") Integer likedUserId) throws Exception;


    @ApiOperation(value = "Deleta um like")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma Mensagem de sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<String> deleteLike(@PathVariable("likeId") Integer likeId) throws Exception;

    @ApiOperation(value = "Deleta os likes de um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma Mensagem de sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<String> deleteLikeByUser(@PathVariable("userId") Integer userId) throws Exception;

    @ApiOperation(value = "Dar like pelo usuário logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o like"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<LikeDTO> darLikeByLogedUser(@PathVariable("likedUserId") Integer likedUserId) throws Exception;

    @ApiOperation(value = "Deleta os likes do usuário logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma Mensagem de sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<String> deleteLikesByLogedUser() throws Exception;
}
