package com.NRG.vende.repository;

import com.NRG.vende.models.UsuarioEntity;
import jakarta.persistence.PostLoad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    public UserDetails findByEmail(String email);
}
