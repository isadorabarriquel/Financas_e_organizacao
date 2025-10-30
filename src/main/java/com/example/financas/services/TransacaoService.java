package com.example.financas.services;
import com.example.financas.dtos.transacao.TransacaoRequestDTO;
import com.example.financas.dtos.transacao.TransacaoResponseDTO;
import com.example.financas.mappers.ContaMapper;
import com.example.financas.mappers.TransacaoMapper;
import com.example.financas.models.Conta;
import com.example.financas.models.Transacao;
import com.example.financas.repositories.RepositorioTransacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransacaoService {
    private final RepositorioTransacao repositorioTransacao;
    private TransacaoMapper transacaoMapper;

    public TransacaoService(RepositorioTransacao repositorioTransacao) {
        this.repositorioTransacao = repositorioTransacao;
    }

    public List<TransacaoResponseDTO> getAllTransacoes(int paginaAtual, int tamanhoPagina) {
        Pageable pageable = PageRequest.of(paginaAtual, tamanhoPagina, Sort.by("criadoEm").descending());
        Page<Transacao> page = repositorioTransacao.findAll(pageable);
        return page.stream().map(TransacaoMapper::toDto).toList();
    }

    public TransacaoResponseDTO getTransacaoPorId(UUID id) {
        Transacao transacao = repositorioTransacao.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada com o ID: " + id));
        return transacaoMapper.toDto(transacao);
    }

    public TransacaoResponseDTO createTransacao(TransacaoRequestDTO dto) {
        Transacao transacao = new Transacao();
        transacao.setUsuarioId(dto.usuarioId());
        transacao.setId(dto.contaId());
        transacao.setCategoriaId(dto.categoriaId());
        transacao.setTipo(dto.tipo());
        transacao.setValor(dto.valor());
        transacao.setData(java.time.LocalDateTime.now());
        transacao.setDescricao(dto.descricao());
        Transacao save = repositorioTransacao.save(transacao);
        return transacaoMapper.toDto(save);
    }
}
