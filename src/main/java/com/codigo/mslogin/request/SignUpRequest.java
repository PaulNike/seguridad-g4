package com.codigo.mslogin.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String nombres;
    private String apellidos;
    private String email;
    private String password;
}
