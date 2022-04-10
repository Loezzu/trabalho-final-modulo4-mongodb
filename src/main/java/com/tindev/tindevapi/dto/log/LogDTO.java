package com.tindev.tindevapi.dto.log;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tindev.tindevapi.enums.TipoLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO {

    private String id;

    private TipoLog tipoLog;

    private String descricao;

    private String data;

}
