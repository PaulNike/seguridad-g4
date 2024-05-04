package com.codigo.mslogin.service.impl;

import com.codigo.mslogin.entities.Usuario;
import com.codigo.mslogin.repository.UsuarioRepository;
import com.codigo.mslogin.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    @Override
    public UserDetailsService userDetailService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return usuarioRepository.findByEmail(username).orElseThrow(()->
                        new UsernameNotFoundException("Usuario no Encontrado"));
            }
        };
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }
}
