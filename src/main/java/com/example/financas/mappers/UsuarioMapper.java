package com.example.financas.mappers;

import com.example.financas.dtos.usuario.UsuarioResponseDTO;
import com.example.financas.models.Usuario;

public class UsuarioMapper {

    public static UsuarioResponseDTO toDto(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCriadoEm(),
                usuario.getAtualizadoEm(),
                usuario.isResumoMensal()   // <– FALTAVA ISSO
        );
    }
}
