package com.codigo.mslogin.entities;

import lombok.Getter;

@Getter
public enum Role {
    USER,
    ADMIN,
    INVITADO,
    SUPERADMIN

  /*  USER(2),
    ADMIN(1);
    private final int value;
    Role(int value) {
        this.value = value;
    }*/
}
