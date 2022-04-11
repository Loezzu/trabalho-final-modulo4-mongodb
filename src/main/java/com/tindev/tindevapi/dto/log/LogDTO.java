package com.tindev.tindevapi.dto.log;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tindev.tindevapi.enums.TipoLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO {

    private String id;

    @Enumerated(EnumType.STRING)
    private TipoLog tipoLog;

    private String descricao;

    private String data;

}
