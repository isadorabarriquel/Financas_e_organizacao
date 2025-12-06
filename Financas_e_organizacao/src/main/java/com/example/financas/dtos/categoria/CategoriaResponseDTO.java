package com.example.financas.dtos.categoria;

import java.time.LocalDateTime;
import java.util.UUID;

public record CategoriaResponseDTO(
        UUID id,
        UUID usuarioId,
        String nome,
        String tipo,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {

}
