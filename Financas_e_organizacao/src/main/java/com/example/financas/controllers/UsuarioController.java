package com.example.financas.controllers;

import com.example.financas.dtos.usuario.UsuarioNotificacaoRequestDTO;
import com.example.financas.dtos.usuario.UsuarioRequestDTO;
import com.example.financas.dtos.usuario.UsuarioResponseDTO;
import com.example.financas.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsuarios(
            @RequestParam(defaultValue = "0") int paginaAtual,
            @RequestParam(defaultValue = "10") int tamanhoPagina,
            @RequestParam(required = false) String nome
    ) {
        List<UsuarioResponseDTO> usuarios =  usuarioService.getAllUsuarios(paginaAtual, tamanhoPagina, nome);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioPorId(@PathVariable UUID id) {
        UsuarioResponseDTO usuario =  usuarioService.getUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> createUsuario(@RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO novoUsuario =  usuarioService.createUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(
            @PathVariable UUID id,
            @RequestBody UsuarioRequestDTO dto
    ) {
        UsuarioResponseDTO usuarioAlterado =  usuarioService.updateUsuario(id, dto);
        return ResponseEntity.ok(usuarioAlterado);
    }

    @PutMapping("/{id}/resumo-mensal")
    public ResponseEntity<UsuarioResponseDTO> atualizaPreferenciaRelatorioMensal(
            @PathVariable UUID id,
            @RequestBody UsuarioNotificacaoRequestDTO dto
    ) {
        UsuarioResponseDTO usuario = usuarioService.preferenciaRelatorioMensal(id, dto);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable UUID id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
