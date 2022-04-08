package com.tindev.tindevapi.dto.user;

import com.tindev.tindevapi.enums.Gender;
import com.tindev.tindevapi.enums.Pref;
import com.tindev.tindevapi.enums.ProgLangs;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserDTOWithoutPassword{

    @ApiModelProperty(value = "The username", example = "Joao123", required = true)
    @NotNull
    @NotEmpty(message = "Username could not be empty")
    private String username;

    @ApiModelProperty(value = "The user's programming language", example = "JAVA", required = true)
    @NotNull
    private ProgLangs progLangs;

    @ApiModelProperty(value = "The user's gender", example = "MALE", required = true)
    @NotNull
    private Gender gender;

    @ApiModelProperty(value = "The user's preference", example = "WOMEN", required = true)
    @NotNull
    private Pref pref;

}
