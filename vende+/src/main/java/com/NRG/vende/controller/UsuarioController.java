package com.NRG.vende.controller;

import com.NRG.vende.models.UsuarioEntity;
import com.NRG.vende.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService s;

    @GetMapping
    public List<UsuarioEntity> findAll() {
        return s.findAll();
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> Registrar(@RequestBody UsuarioEntity data) {
        String encryptedSenha = new BCryptPasswordEncoder().encode(data.getPassword());
        UsuarioEntity novoUsuario = new UsuarioEntity(data.getEmail(), data.getNome(), encryptedSenha);
        s.registrar(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(Map.of("message","Usuario registrado"));
    }
}
