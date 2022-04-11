package com.tindev.tindevapi.client;


import com.tindev.tindevapi.dto.log.LogDTO;
import com.tindev.tindevapi.dto.log.LogDTOContador;
import com.tindev.tindevapi.enums.TipoLog;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(value="log-tindev-api", url="https://tindev-mongodb.herokuapp.com")
@Headers("Content-Type: application/json")
public interface LogTindevClient {

    @RequestLine("GET /log/list")
    List<LogDTO> listAll();

    @RequestLine("GET /log/list-by-tipo-log?tipoLog={tipoLog}")
    List<LogDTO> listByTipoLog(@Param("tipoLog") String tipoLog);

    @RequestLine("GET /log/count-by-tipo-log")
    List<LogDTOContador> groupByTipoLogAndCount();

    @RequestLine("GET /log/find-all-by-data?data={data}")
    List<LogDTO> getByData(@Param("data") String data);

    @RequestLine("GET /log/count-logs-by-tipo?tipoLog={tipoLog}")
    LogDTOContador getCountByTipoLog(@Param("tipoLog") String tipoLog);

    @RequestLine("POST /log/save-log?descricao={descricao}&tipoLog={tipoLog}")
    void logPost(@Param("descricao") String descricao, @Param("tipoLog") TipoLog tipoLog);





}




