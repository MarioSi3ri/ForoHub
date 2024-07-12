package com.si3ri.ForoHub.infra.repository;

import com.si3ri.ForoHub.domain.entity.usuarios.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios,Long> {
    UserDetails findByEmail(String username);
}
