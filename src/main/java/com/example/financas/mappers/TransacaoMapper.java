package com.example.financas.mappers;

import com.example.financas.dtos.transacao.TransacaoResponseDTO;
import com.example.financas.models.Transacao;

public class TransacaoMapper {

    public static TransacaoResponseDTO toDto(Transacao t) {
        return new TransacaoResponseDTO(
                t.getId(),
                t.getUsuarioId(),
                t.getContaId(),
                t.getCategoriaId(),
                t.getTipo(),
                t.getValor(),
                t.getData(),
                t.getDescricao(),
                t.getCriadoEm(),
                t.getAtualizadoEm()
        );
    }
}
