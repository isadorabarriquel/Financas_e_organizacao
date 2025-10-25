package com.example.financas.dtos.conta;

import java.util.UUID;
import java.time.LocalDateTime;

public record ContaResponseDTO(
        UUID id,
        UUID usuarioId,
        String nome,
        String tipo,
        String moeda,
        Double saldo,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {

}
