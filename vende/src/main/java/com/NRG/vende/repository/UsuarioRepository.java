package com.NRG.vende.repository;

import com.NRG.vende.models.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    public UserDetails findByEmail(String email);
}
