package com.codigo.mslogin.repository;

import com.codigo.mslogin.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol,Long> {
    Optional<Rol> findByNombreRol(String rol);
}
