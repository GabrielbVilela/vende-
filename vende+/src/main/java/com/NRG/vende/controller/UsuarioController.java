package com.NRG.vende.controller;

import com.NRG.vende.models.usuario.UsuarioEntity;
import com.NRG.vende.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService s;

    @GetMapping
    public List<UsuarioEntity> findAll() {
        return s.findAll();
    }

}
