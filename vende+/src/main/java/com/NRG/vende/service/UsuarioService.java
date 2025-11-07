package com.NRG.vende.service;

import com.NRG.vende.models.UsuarioEntity;
import com.NRG.vende.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PostLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository r;

    public Optional<UsuarioEntity> login(String email, String password) {
        var usuario = r.findByEmail(email);
        if (usuario.isEmpty()) { return Optional.empty();}
        if (!usuario.get().getSenha().equals(password)){ return Optional.empty(); }
        return usuario;
    }

    public UsuarioEntity registrar(UsuarioEntity usuario) {
        try {
            r.save(usuario);
            return usuario;
        } catch (Exception e) {
            throw new RuntimeException("erro ao registrar usuario");
        }
    }
}
