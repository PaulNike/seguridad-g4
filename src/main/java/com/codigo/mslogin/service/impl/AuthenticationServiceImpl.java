package com.codigo.mslogin.service.impl;

import com.codigo.mslogin.entities.Rol;
import com.codigo.mslogin.entities.Role;
import com.codigo.mslogin.entities.Usuario;
import com.codigo.mslogin.repository.RolRepository;
import com.codigo.mslogin.repository.UsuarioRepository;
import com.codigo.mslogin.request.SignUpRequest;
import com.codigo.mslogin.service.AuthenticationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;


    @Transactional
    @Override
    public Usuario signUpUser(SignUpRequest signUpRequest) {
        Usuario usuario = new Usuario();
        usuario.setNombres(signUpRequest.getNombres());
        usuario.setApellidos(signUpRequest.getApellidos());
        usuario.setEmail(signUpRequest.getEmail());
        //Defino mi lista de roles
        Set<Rol> assignedRoles = new HashSet<>();
        //Busco el rol con el apoyo del enum
        Rol userRole = rolRepository.findByNombreRol(Role.USER.name()).orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
        //agrego el rol encontrado a mi SET
        assignedRoles.add(userRole);
        //Seteando ROl a mi entidad USER
        usuario.setRoles(assignedRoles);
        usuario.setPassword(signUpRequest.getPassword());
        return usuarioRepository.save(usuario);

    }

    @Override
    public Usuario signUpAdmin(SignUpRequest signUpRequest) {
        Usuario usuario = new Usuario();

        usuario.setNombres(signUpRequest.getNombres());
        usuario.setApellidos(signUpRequest.getApellidos());
        usuario.setEmail(signUpRequest.getEmail());
        Set<Rol> assignedRoles = new HashSet<>();
        Rol userRole = rolRepository.findByNombreRol(Role.ADMIN.name()).orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
        assignedRoles.add(userRole);
        usuario.setRoles(assignedRoles);
        usuario.setPassword(signUpRequest.getPassword());
        return usuarioRepository.save(usuario);

    }

}
