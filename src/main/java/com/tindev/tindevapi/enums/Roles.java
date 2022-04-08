package com.tindev.tindevapi.enums;

import io.swagger.models.auth.In;

public enum Roles {

    FREE(1),
    PRO(2);

    private final Integer idRole;

    Roles(Integer idRole){
        this.idRole = idRole;
    }

    public Integer getRole(){
        return idRole;
    }

}
