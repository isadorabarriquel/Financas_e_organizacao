package com.example.financas.controllers;

import com.example.financas.dtos.usuario.UsuarioResponseDTO;
import com.example.financas.services.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping
    public List<UsuarioResponseDTO> getAllUsuarios(
            @RequestParam(defaultValue = "0") int paginaAtual,
            @RequestParam(defaultValue = "10") int tamanhoPagina
    ) {
        return UsuarioService.getAllUsuarios(paginaAtual, tamanhoPagina);
    }
}
