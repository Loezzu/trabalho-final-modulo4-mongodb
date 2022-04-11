package com.tindev.tindevapi.service;

import com.tindev.tindevapi.client.LogTindevClient;
import com.tindev.tindevapi.dto.log.LogDTO;
import com.tindev.tindevapi.dto.log.LogDTOContador;
import com.tindev.tindevapi.enums.TipoLog;
import com.tindev.tindevapi.repository.exceptions.RegraDeNegocioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class LogService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    private final LogTindevClient logTindevClient;

    public List<LogDTO> listAll() {
        return logTindevClient.listAll();
    }

    public List<LogDTO> listByTipoLog(TipoLog tipoLog) {
        return logTindevClient.listByTipoLog(tipoLog.toString());
    }

    public List<LogDTOContador> groupByTipoLogAndCount(){
        return logTindevClient.groupByTipoLogAndCount();
    }

    public List<LogDTO> getByData(String data) throws Exception {
        Date dataAtual = new Date();
        Date dateReceived = sdf.parse(data);
        if(dateReceived.after(dataAtual)){
            throw new RegraDeNegocioException("Esse dia não chegou!");
        }
        return logTindevClient.getByData(data);
    }

    public LogDTOContador getCountByTipoLog(TipoLog tipoLog){
        return logTindevClient.getCountByTipoLog(tipoLog.toString());
    }

    public void logPost(TipoLog tipoLog, String descricao) {
        logTindevClient.logPost(descricao, tipoLog);
    }
}
