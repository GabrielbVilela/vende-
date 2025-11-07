package com.NRG.vende.repository;

import com.NRG.vende.models.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    public Optional<UsuarioEntity> findByEmail(String email);
}
