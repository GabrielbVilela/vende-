package com.NRG.vende.service;

import com.NRG.vende.models.usuario.UsuarioCargoEnum;
import com.NRG.vende.models.usuario.UsuarioEntity;
import com.NRG.vende.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        String senhaCipher = new BCryptPasswordEncoder().encode("123456");
        var usuario = new UsuarioEntity("admin@pdv.com", "Admin", senhaCipher, UsuarioCargoEnum.ADMIN);
        r.save(usuario);
    }
}
