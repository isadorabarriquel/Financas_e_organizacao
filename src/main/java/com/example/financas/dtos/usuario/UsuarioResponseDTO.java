package com.example.financas.dtos.usuario;

import java.time.LocalDateTime;
import java.util.UUID;
public record UsuarioResponseDTO(
        UUID id,
        String nome,
        String email,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
