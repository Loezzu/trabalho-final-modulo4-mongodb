package com.tindev.tindevapi.dto.log;

import com.tindev.tindevapi.enums.TipoLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogDTOContador {

    private TipoLog tipoLog;
    private Integer quantidade;

}
