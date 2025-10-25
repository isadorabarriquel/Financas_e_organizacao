package com.example.financas.services;

import com.example.financas.dtos.usuario.UsuarioResponseDTO;
import com.example.financas.mappers.UsuarioMapper;
import com.example.financas.models.Usuario;
import com.example.financas.repositories.RepositorioUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final RepositorioUsuario repositorioUsuario;

    public UsuarioService(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public List<UsuarioResponseDTO> getAllUsuarios(int paginaAtual, int tamanhoPagina) {
        Pageable pageable = PageRequest.of(paginaAtual, tamanhoPagina, Sort.by("nome"));
        Page<Usuario> page = repositorioUsuario.findAll(pageable);
        return page.stream().map(UsuarioMapper::toDto).toList();
    }
}
