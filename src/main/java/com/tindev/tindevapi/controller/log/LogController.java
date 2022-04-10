package com.tindev.tindevapi.controller.log;

import com.tindev.tindevapi.dto.log.LogDTO;
import com.tindev.tindevapi.enums.TipoLog;
import com.tindev.tindevapi.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @GetMapping("/list")
    public List<LogDTO> getAll() {
        return logService.listAll();
    }

    @GetMapping("/list-by-tipo")
    public List<LogDTO> listByTipo(@RequestParam TipoLog tipoLog) {
        return logService.listByTipoLog(tipoLog);
    }

    @PostMapping("/saveLog")
    public void save(@RequestParam String descricao, @RequestParam TipoLog tipoLog) {
        logService.logPost(tipoLog, descricao);
    }
}



