package com.example.financas.controllers;

import com.example.financas.dtos.usuario.UsuarioResponseDTO;
import com.example.financas.services.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioResponseDTO> getAllUsuarios(
            @RequestParam(defaultValue = "0") int paginaAtual,
            @RequestParam(defaultValue = "10") int tamanhoPagina
    ) {
        return usuarioService.getAllUsuarios(paginaAtual, tamanhoPagina);
    }
}
