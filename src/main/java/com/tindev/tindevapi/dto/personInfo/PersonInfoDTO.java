package com.tindev.tindevapi.dto.personInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonInfoDTO extends PersonInfoCreateDTO {

    @ApiModelProperty(value = "Id of the user", example = "1")
    private Integer idPersonInfo;

}
