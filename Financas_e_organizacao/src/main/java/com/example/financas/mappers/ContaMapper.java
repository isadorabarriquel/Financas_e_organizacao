package com.example.financas.mappers;

import com.example.financas.dtos.conta.ContaResponseDTO;
import com.example.financas.models.Conta;

public class ContaMapper {

    public static ContaResponseDTO toDto(Conta conta) {
        // Converte BigDecimal -> Double (e trata null só por segurança)
        Double saldo = conta.getSaldo() != null ? conta.getSaldo().doubleValue() : null;

        return new ContaResponseDTO(
                conta.getId(),
                conta.getUsuarioId(),
                conta.getNome(),
                conta.getTipo(),
                conta.getMoeda(),
                saldo,
                conta.getCriadoEm(),
                conta.getAtualizadoEm()
        );
    }
}
