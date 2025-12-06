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
import com.example.financas.repositories.RepositorioTransacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class CategoriaService {
    private final RepositorioCategoria repositorioCategoria;
    private RepositorioTransacao repositorioTransacao;
    /*private CategoriaMapper categoriaMapper;*/

    public CategoriaService(RepositorioCategoria repositorioCategoria) {
        this.repositorioCategoria = repositorioCategoria;
    }

    public List<CategoriaResponseDTO> getAllCategorias(
            int paginaAtual,
            int tamanhoPagina,
            String nome,
            String tipo) {
        Pageable pageable = PageRequest.of(paginaAtual, tamanhoPagina, Sort.by("nome").ascending());
        Page<Categoria> page = repositorioCategoria.findAll(pageable);

        Stream<Categoria> stream = page.stream();

        if (tipo != null && !tipo.isBlank()) {
            String tipoUpper = tipo.toUpperCase();
            stream = stream.filter(c -> c.getTipo() != null && c.getTipo().toUpperCase().equals(tipoUpper));
        }

        return stream
                .map(CategoriaMapper::toDto)
                .toList();
    }

    public CategoriaResponseDTO createCategoria(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        categoria.setTipo(dto.tipo());
        categoria.setCriadoEm(java.time.LocalDateTime.now());
        categoria.setAtualizadoEm(java.time.LocalDateTime.now());
        Categoria save = repositorioCategoria.save(categoria);
        return CategoriaMapper.toDto(save);
    }

    public CategoriaResponseDTO updateCategoria(UUID id, CategoriaRequestDTO dto) {
        Categoria categoria = repositorioCategoria.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));

        categoria.setNome(dto.nome());
        categoria.setTipo(dto.tipo());
        categoria.setAtualizadoEm(java.time.LocalDateTime.now());

        Categoria updated = repositorioCategoria.save(categoria);
        return CategoriaMapper.toDto(updated);
    }

    public void deleteCategoria(UUID id) {

        Categoria categoria = repositorioCategoria.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));

        boolean existeTransacaoComCategoria = repositorioTransacao.existsByCategoriaId(id);

        if (existeTransacaoComCategoria) {
            throw new RuntimeException(
                    "A categoria não pode ser excluída pois está sendo usada em transações."
            );
        }

        repositorioCategoria.deleteById(id);
    }

}

