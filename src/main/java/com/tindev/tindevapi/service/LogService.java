package com.tindev.tindevapi.service;

import com.tindev.tindevapi.client.LogTindevClient;
import com.tindev.tindevapi.dto.log.LogDTO;
import com.tindev.tindevapi.enums.TipoLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class LogService {

    private final LogTindevClient logTindevClient;

    public List<LogDTO> listAll() {
        return logTindevClient.listAll();
    }

    public List<LogDTO> listByTipoLog(TipoLog tipoLog) {
        return logTindevClient.listByTipoLog(tipoLog.toString());
    }

    public void logPost(TipoLog tipoLog, String descricao) {
        logTindevClient.logPost(descricao, tipoLog);
    }
}
