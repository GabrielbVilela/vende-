package com.NRG.vende.service;

import com.NRG.vende.models.UsuarioEntity;
import com.NRG.vende.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository r;

    public UsuarioEntity registrar(UsuarioEntity usuario) {
        try {
            r.save(usuario);
            return usuario;
        } catch (Exception e) {
            throw new RuntimeException("erro ao registrar usuario");
        }
    }

    public List<UsuarioEntity> findAll() {
        return r.findAll();
    }

    @PostConstruct
    public void init() {
        var usuario = new UsuarioEntity("Email", "Nome", "Senha","admin");
        r.save(usuario);
        usuario = new UsuarioEntity("Email2", "Nome2", "123","user");
        r.save(usuario);
    }
}
