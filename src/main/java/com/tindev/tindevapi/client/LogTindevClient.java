package com.tindev.tindevapi.client;


import com.tindev.tindevapi.dto.log.LogDTO;
import com.tindev.tindevapi.enums.TipoLog;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value="log-tindev-api", url="https://tindev-mongodb.herokuapp.com")
@Headers("Content-Type: application/json")
public interface LogTindevClient {

    @RequestLine("GET log/list")
    List<LogDTO> listAll();

   @RequestLine("GET /log/list-by-tipo-log")
   List<LogDTO> listByTipoLog(TipoLog tipoLog);

    @RequestLine("POST /log/save-user?descricao={descricao}")
    void logUser(@Param("descricao") String descricao);


    @RequestLine("POST /log/save-log?descricao={descricao}&tipoLog={tipoLog}")
    void logPost(@Param("descricao") String descricao, @Param("tipoLog") TipoLog tipoLog);





}




