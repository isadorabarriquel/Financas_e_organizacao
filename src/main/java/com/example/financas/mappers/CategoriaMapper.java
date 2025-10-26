package com.example.financas.mappers;

import com.example.financas.dtos.categoria.CategoriaResponseDTO;
import com.example.financas.models.Categoria;

public class CategoriaMapper {
    public static CategoriaResponseDTO toDto(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getUsuarioId(),
                categoria.getNome(),
                categoria.getTipo(),
                categoria.getAtualizadoEm(),
                categoria.getCriadoEm()
        );
    }
}
