package com.tindev.tindevapi.controller.log;

import com.tindev.tindevapi.dto.log.LogDTO;
import com.tindev.tindevapi.dto.log.LogDTOContador;
import com.tindev.tindevapi.dto.match.MatchDTO;
import com.tindev.tindevapi.enums.TipoLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api
@Validated
public interface LogAPI {

    @ApiOperation(value = "Mostra uma lista de logs")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de logs"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    List<LogDTO> getAll();

    @ApiOperation(value = "Quantidade de todos os tipos de logs")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de tipo de logs e a quantidade de chamadas"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    List<LogDTOContador> groupByTipoLogAndCount();

    @ApiOperation(value = "Número de logs de um tipo específico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o número de likes de um tipo específico"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    LogDTOContador getCountByTipoLog(@RequestParam TipoLog tipoLog);


    @ApiOperation(value = "Retorna os logs por data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os logs por data"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    List<LogDTO> getByData(@RequestParam String data) throws Exception;

    @ApiOperation(value = "Cria um log")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tudo certo!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    void save(@RequestParam String descricao, @RequestParam TipoLog tipoLog);
}
