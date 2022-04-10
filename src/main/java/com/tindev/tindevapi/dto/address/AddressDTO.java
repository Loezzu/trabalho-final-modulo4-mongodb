package com.tindev.tindevapi.dto.address;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO extends AddressCreateDTO{

    @ApiModelProperty(value = "The ID of the address", example = "1")
    private Integer idAddress;

}