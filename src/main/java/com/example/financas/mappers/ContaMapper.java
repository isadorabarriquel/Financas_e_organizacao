package com.example.financas.mappers;

import com.example.financas.dtos.conta.ContaResponseDTO;
import com.example.financas.models.Conta;

public class ContaMapper {
    public static ContaResponseDTO toDto(Conta conta) {
        return new ContaResponseDTO(
                conta.getId(),
                conta.getUsuarioId(),
                conta.getNome(),
                conta.getTipo(),
                conta.getMoeda(),
                conta.getSaldo(),
                conta.getAtualizadoEm(),
                conta.getCriadoEm()
        );
    }

}
