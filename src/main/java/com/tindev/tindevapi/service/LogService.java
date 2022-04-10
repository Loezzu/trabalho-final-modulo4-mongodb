package com.tindev.tindevapi.service;

import com.tindev.tindevapi.client.LogTindevClient;
import com.tindev.tindevapi.dto.log.LogDTO;
import com.tindev.tindevapi.enums.TipoLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogTindevClient logTindevClient;

    public List<LogDTO> listAll() {
        return logTindevClient.listAll();
    }

    public List<LogDTO> listByTipoLog(TipoLog tipoLog) {
        return logTindevClient.listByTipoLog(tipoLog);
    }

    public void logUser(String descricao) {
        logTindevClient.logUser(descricao);
    }
}
