package com.tindev.tindevapi.dto.user;

import com.tindev.tindevapi.enums.Gender;
import com.tindev.tindevapi.enums.Pref;
import com.tindev.tindevapi.enums.ProgLangs;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserCreateDTO {

    @ApiModelProperty(value = "The user's personal identification ID", example = "1", required = true)
    @NotNull
    private Integer PersoInfoId;

    @ApiModelProperty(value = "The user's address ID", example = "1", required = true)
    @NotNull
    private Integer AddressId;

    @ApiModelProperty(value = "The username", example = "Joao123", required = true)
    @NotNull
    @NotEmpty(message = "Username could not be empty")
    private String username;

    @ApiModelProperty(value = "The user's password", example = "123@mgp", required = true)
    @NotNull
    @NotEmpty(message = "Password could not be empty")
    private String password;

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
