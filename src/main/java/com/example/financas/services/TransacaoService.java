package com.example.financas.services;

import com.example.financas.dtos.transacao.TransacaoResponseDTO;
import com.example.financas.mappers.TransacaoMapper;
import com.example.financas.models.Transacao;
import com.example.financas.repositories.RepositorioTransacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {
    private final RepositorioTransacao repositorioTransacao;

    public TransacaoService(RepositorioTransacao repositorioTransacao) {
        this.repositorioTransacao = repositorioTransacao;
    }

    public List<TransacaoResponseDTO> getAllTransacoes(int paginaAtual, int tamanhoPagina) {
        Pageable pageable = PageRequest.of(paginaAtual, tamanhoPagina, Sort.by("criadoEm").descending());
        Page<Transacao> page = repositorioTransacao.findAll(pageable);
        return page.stream().map(TransacaoMapper::toDto).toList();
    }
}
