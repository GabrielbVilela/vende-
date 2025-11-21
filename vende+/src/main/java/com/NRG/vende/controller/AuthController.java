package com.NRG.vende.controller;

import com.NRG.vende.infra.TokenService;
import com.NRG.vende.models.AuthDTO;
import com.NRG.vende.models.LoginResponseDTO;
import com.NRG.vende.models.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    TokenService tokenService;
    @Autowired
    private AuthenticationManager aM;
    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody AuthDTO data) {
        var UsuarioSenha = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.aM.authenticate(UsuarioSenha);
        var token = tokenService.generateToken((UsuarioEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
