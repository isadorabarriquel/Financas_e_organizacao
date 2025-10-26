package com.example.financas.services;

import com.example.financas.dtos.categoria.CategoriaRequestDTO;
import com.example.financas.dtos.categoria.CategoriaResponseDTO;
import com.example.financas.dtos.conta.ContaRequestDTO;
import com.example.financas.dtos.conta.ContaResponseDTO;
import com.example.financas.mappers.CategoriaMapper;
import com.example.financas.mappers.ContaMapper;
import com.example.financas.models.Categoria;
import com.example.financas.models.Conta;
import com.example.financas.repositories.RepositorioCategoria;
import com.example.financas.repositories.RepositorioConta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {
    private final RepositorioCategoria repositorioCategoria;
    private CategoriaMapper categoriaMapper;

    public CategoriaService(
            RepositorioCategoria repositorioCategoria
    ) {
        this.repositorioCategoria = repositorioCategoria;
    }

    public List<CategoriaResponseDTO> getAllCategorias(int paginaAtual, int tamanhoPagina) {
        Pageable pageable = PageRequest.of(paginaAtual, tamanhoPagina, Sort.by("nome"));
        Page<Categoria> page = repositorioCategoria.findAll(pageable);
        return page.stream().map(CategoriaMapper::toDto).toList();
    }

    public CategoriaResponseDTO createCategoria(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        categoria.setTipo(dto.tipo());
        categoria.setCriadoEm(java.time.LocalDateTime.now());
        categoria.setAtualizadoEm(java.time.LocalDateTime.now());
        Categoria save = repositorioCategoria.save(categoria);
        return categoriaMapper.toDto(save);
    }

    public CategoriaResponseDTO updateCategoria(UUID id, CategoriaRequestDTO dto) {
        Categoria categoria = repositorioCategoria.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));

        categoria.setNome(dto.nome());
        categoria.setTipo(dto.tipo());
        categoria.setAtualizadoEm(java.time.LocalDateTime.now());

        Categoria updated = repositorioCategoria.save(categoria);
        return categoriaMapper.toDto(updated);
    }

    public void deleteCategoria(UUID id) {
        if (!repositorioCategoria.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada com o ID: " + id);
        }
        repositorioCategoria.deleteById(id);
    }
}

