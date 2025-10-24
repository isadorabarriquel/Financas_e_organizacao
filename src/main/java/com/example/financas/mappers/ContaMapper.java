package com.example.financas.mappers;

import com.example.financas.dtos.conta.ContaResponseDTO;
import com.example.financas.models.Conta;

public class ContaMapper {
    public ContaResponseDTO toDto(Conta conta) {
        return new ContaResponseDTO(
                conta.getId(),
                conta.getAtualizadoEm(),
                conta.getCriadoEm()
        );
    }

}
