package com.tindev.tindevapi.controller.log;

import com.tindev.tindevapi.dto.log.LogDTO;
import com.tindev.tindevapi.dto.log.LogDTOContador;
import com.tindev.tindevapi.enums.TipoLog;
import com.tindev.tindevapi.service.LogService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
@Api(value = "6 - Log API", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"6 - Log API"}, description = "Log Controls")
public class LogController implements LogAPI{

    private final LogService logService;

    @GetMapping("/list")
    public List<LogDTO> getAll() {
        return logService.listAll();
    }

    @GetMapping("/list-by-tipo")
    public List<LogDTO> listByTipo(@RequestParam TipoLog tipoLog) {
        return logService.listByTipoLog(tipoLog);
    }

    @GetMapping("/group-logs-and-count")
    public List<LogDTOContador> groupByTipoLogAndCount(){
        return logService.groupByTipoLogAndCount();
    }

    @GetMapping("/count-logs-by-type")
    public LogDTOContador getCountByTipoLog(@RequestParam TipoLog tipoLog){
        return logService.getCountByTipoLog(tipoLog);
    }

    @GetMapping("/get-by-data")
    public List<LogDTO> getByData(@RequestParam String data) throws Exception {
        return logService.getByData(data);
    }

    @PostMapping("/saveLog")
    public void save(@RequestParam String descricao, @RequestParam TipoLog tipoLog) {
        logService.logPost(tipoLog, descricao);
    }
}



