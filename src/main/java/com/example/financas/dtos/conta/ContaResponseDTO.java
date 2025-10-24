package com.example.financas.dtos.conta;

import java.util.UUID;

public record ContaResponseDTO(
        UUID id,
        UUID usuarioId,
        String nome,
        String tipo,
        String moeda,
        Double saldo,
        String criadoEm,
        String atualizadoEm
) {

}
