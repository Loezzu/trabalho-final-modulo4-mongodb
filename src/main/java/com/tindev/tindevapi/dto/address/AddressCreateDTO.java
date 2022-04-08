package com.tindev.tindevapi.dto.address;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AddressCreateDTO {

    @ApiModelProperty(value = "The street", example = "Rua amapa", required = true)
    @NotEmpty(message = "Street could not be empty")
    private String street;

    @ApiModelProperty(value = "The number of the house", example = "12", required = true)
    @NotNull(message = "Number could not be empty")
    private long number;

    @ApiModelProperty(value = "The city", example = "Cachoeirinha", required = true)
    @NotEmpty(message = "City could not be empty")
    private String city;

    @ApiModelProperty(value = "The postal code", example = "94920-100", required = true)
    @Pattern(regexp = "[0-9]{5}-[0-9]{3}", message = "CEP invalido")
    private String cep;

}
