package com.example.financas.dtos.transacao;

import java.time.LocalDateTime;
import java.util.UUID;

public record TransacaoResponseDTO(
        UUID id,
        UUID usuarioId,
        UUID contaId,
        UUID categoriaId,
        String tipo,
        Double valor,
        LocalDateTime data,
        String descricao,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
