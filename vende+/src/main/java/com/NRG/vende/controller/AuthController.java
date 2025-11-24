package com.NRG.vende.controller;

import com.NRG.vende.infra.TokenService;
import com.NRG.vende.dto.AuthDTO;
import com.NRG.vende.dto.LoginResponseDTO;
import com.NRG.vende.models.usuario.UsuarioEntity;
import com.NRG.vende.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    TokenService tokenService;
    @Autowired
    private AuthenticationManager aM;
    @Autowired
    private UsuarioService s;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthDTO data) {
        var UsuarioSenha = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.aM.authenticate(UsuarioSenha);
        var token = tokenService.generateToken((UsuarioEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
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
