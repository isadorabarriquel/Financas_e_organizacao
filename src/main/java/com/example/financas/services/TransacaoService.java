package com.example.financas.services;
import com.example.financas.dtos.conta.ContaRequestDTO;
import com.example.financas.dtos.conta.ContaResponseDTO;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class TransacaoService {
    private final RepositorioTransacao repositorioTransacao;
    /*private TransacaoMapper transacaoMapper;*/

    public TransacaoService(RepositorioTransacao repositorioTransacao) {
        this.repositorioTransacao = repositorioTransacao;
    }

    public List<TransacaoResponseDTO> getAllTransacoes(
            int paginaAtual,
            int tamanhoPagina,
            String tipo,
            UUID contaId,
            UUID categoriaId,
            UUID usuarioId) {
        Pageable pageable = PageRequest.of(paginaAtual, tamanhoPagina, Sort.by("criadoEm").descending());
        Page<Transacao> page = repositorioTransacao.findAll(pageable);
        /*return page.stream().map(TransacaoMapper::toDto).toList();*/
        Stream<Transacao> stream = page.stream();

        if (tipo != null && !tipo.isBlank()) {
            String tipoLower = tipo.toLowerCase();
            stream = stream.filter(t -> t.getTipo() != null &&
                    t.getTipo().toLowerCase().equals(tipoLower));
        }
        if (contaId != null) {
            stream = stream.filter(t -> contaId.equals(t.getContaId()));
        }
        if (categoriaId != null) {
            stream = stream.filter(t -> categoriaId.equals(t.getCategoriaId()));
        }
        if (usuarioId != null) {
            stream = stream.filter(t -> usuarioId.equals(t.getUsuarioId()));
        }

        return stream
                .map(TransacaoMapper::toDto) // uso do mapper estático
                .toList();
    }

    public TransacaoResponseDTO getTransacaoPorId(UUID id) {
        Transacao transacao = repositorioTransacao.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada com o ID: " + id));
        return TransacaoMapper.toDto(transacao);
    }

    public TransacaoResponseDTO createTransacao(TransacaoRequestDTO dto) {
        Transacao transacao = new Transacao();
        transacao.setUsuarioId(dto.usuarioId());
        transacao.setContaId(dto.contaId());
        transacao.setCategoriaId(dto.categoriaId());
        transacao.setTipo(dto.tipo());
        transacao.setValor(dto.valor());
        transacao.setData(LocalDateTime.now());
        transacao.setDescricao(dto.descricao());
        Transacao save = repositorioTransacao.save(transacao);
        return TransacaoMapper.toDto(save);
    }

    public TransacaoResponseDTO updateTransacao(UUID id, TransacaoRequestDTO dto) {
        Transacao transacao = repositorioTransacao.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada com o ID: " + id));

        transacao.setValor(dto.valor());
        transacao.setDescricao(dto.descricao());

        Transacao updated = repositorioTransacao.save(transacao);
        return TransacaoMapper.toDto(updated);
    }

    public void deleteTransacao(UUID id) {
        if (!repositorioTransacao.existsById(id)) {
            throw new RuntimeException("Transação não encontrada com o ID: " + id);
        }
        repositorioTransacao.deleteById(id);
    }
}
