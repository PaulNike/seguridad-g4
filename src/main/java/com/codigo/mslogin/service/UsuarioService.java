package com.codigo.mslogin.service;

import com.codigo.mslogin.entities.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioService {
    UserDetailsService userDetailService();
    List<Usuario> getUsuarios();
}
