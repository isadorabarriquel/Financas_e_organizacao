package com.example.financas.mappers;

import com.example.financas.dtos.transacao.TransacaoResponseDTO;
import com.example.financas.models.Transacao;

public class TransacaoMapper {

    public static TransacaoResponseDTO toDto(Transacao t) {


        Double valor = t.getValor() != null ? t.getValor().doubleValue() : null;

        return new TransacaoResponseDTO(
                t.getId(),
                t.getUsuarioId(),
                t.getConta() != null ? t.getConta().getId() : null,
                t.getCategoriaId(),
                t.getTipo(),
                valor,
                t.getData(),
                t.getDescricao(),
                t.getCriadoEm(),
                t.getAtualizadoEm()
        );
    }
}
