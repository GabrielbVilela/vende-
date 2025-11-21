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

//    @PostMapping("/login")
//    public ResponseEntity<?> Login(@RequestBody UsuarioEntity usuario) {
//        var user = s.login(usuario.getEmail(), usuario.getSenha());
//        if (user.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
//                    body(Map.of("message","Email ou senha incorretos"));
//        }
//        return ResponseEntity.ok(Map.of("message","Usuário Encontrado"));
//    }

    @PostMapping("/registrar")
    public ResponseEntity<?> Registrar(@RequestBody UsuarioEntity data) {
        String encryptedSenha = new BCryptPasswordEncoder().encode(data.getPassword());
        UsuarioEntity novoUsuario = new UsuarioEntity(data.getEmail(), data.getNome(), encryptedSenha, "admin");
        s.registrar(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(Map.of("message","Usuario registrado"));
    }
}
