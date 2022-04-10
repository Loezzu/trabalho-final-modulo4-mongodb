package com.tindev.tindevapi.dto.user;

import com.tindev.tindevapi.entities.AddressEntity;
import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
import lombok.Data;

@Data
public class UserDTOCompleto extends UserDTOWithoutPassword{

    private Integer userId;
    AddressEntity addressDTO;
    PersonInfoDTO personInfoDTO;
}
