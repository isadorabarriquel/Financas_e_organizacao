package com.example.financas.controllers;

import com.example.financas.dtos.usuario.UsuarioRequestDTO;
import com.example.financas.dtos.usuario.UsuarioResponseDTO;
import com.example.financas.services.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
            @RequestParam(defaultValue = "10") int tamanhoPagina,
            @RequestParam(required = false) String nome
    ) {
        return usuarioService.getAllUsuarios(paginaAtual, tamanhoPagina, nome);
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO getUsuarioPorId(@PathVariable UUID id) {
        return usuarioService.getUsuarioPorId(id);
    }

    @PostMapping
    public UsuarioResponseDTO createUsuario(@RequestBody UsuarioRequestDTO dto) {
        return usuarioService.createUsuario(dto);
    }

    public UsuarioResponseDTO updateUsuario(
            @PathVariable UUID id,
            @RequestBody UsuarioRequestDTO dto
    ) {
        return usuarioService.updateUsuario(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable UUID id) {
        usuarioService.deleteUsuario(id);
    }
}
