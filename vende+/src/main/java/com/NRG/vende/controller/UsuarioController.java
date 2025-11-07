package com.NRG.vende.controller;

import com.NRG.vende.models.UsuarioEntity;
import com.NRG.vende.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService s;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody UsuarioEntity usuario) {
        var user = s.login(usuario.getEmail(), usuario.getSenha());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                    body(Map.of("message","Email ou senha incorretos"));
        }
        return ResponseEntity.ok(Map.of("message","Usuário Encontrado"));
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> Registrar(@RequestBody UsuarioEntity usuario) {
        s.registrar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(Map.of("message","Usuario registrado","usario",usuario));
    }
}
