package com.tindev.tindevapi.dto.user;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends UserCreateDTO {

    @ApiModelProperty(value = "The user's ID", example = "1")
    private Integer userId;



}
