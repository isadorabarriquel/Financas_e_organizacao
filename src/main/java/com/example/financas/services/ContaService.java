package com.example.financas.services;

import com.example.financas.dtos.conta.ContaRequestDTO;
import com.example.financas.dtos.conta.ContaResponseDTO;
import com.example.financas.mappers.ContaMapper;
import com.example.financas.models.Conta;
import com.example.financas.repositories.RepositorioConta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ContaService {
    private final RepositorioConta repositorioConta;
    /*private ContaMapper contaMapper;*/

    public ContaService(
            RepositorioConta repositorioConta
    ) {
        this.repositorioConta = repositorioConta;
    }

    public List<ContaResponseDTO> getAllContas(int paginaAtual, int tamanhoPagina, String nome, String tipo) {
        Pageable pageable = PageRequest.of(paginaAtual, tamanhoPagina, Sort.by("nome").ascending());
        Page<Conta> page = repositorioConta.findAll(pageable);
        /*return page.stream().map(ContaMapper::toDto).toList();*/

        Stream<Conta> stream = page.stream();

        if (nome != null && !nome.isBlank()) {
            String nomeUpper = nome.toUpperCase();
            stream = stream.filter(c -> c.getNome() != null &&
                    c.getNome().toUpperCase().contains(nomeUpper));
        }
        return stream
                .map(ContaMapper::toDto)
                .toList();
    }

    public ContaResponseDTO getContaPorId(UUID id) {
        Conta conta = repositorioConta.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada com o ID: " + id));
        return ContaMapper.toDto(conta);
    }

    public ContaResponseDTO createConta(ContaRequestDTO dto) {
        Conta conta = new Conta();
        conta.setNome(dto.nome());
        conta.setTipo(dto.tipo());
        conta.setSaldo(dto.saldo());
        conta.setCriadoEm(java.time.LocalDateTime.now());
        conta.setAtualizadoEm(java.time.LocalDateTime.now());
        Conta save = repositorioConta.save(conta);
        return ContaMapper.toDto(save);
    }

    public ContaResponseDTO updateConta(UUID id, ContaRequestDTO dto) {
        Conta conta = repositorioConta.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada com o ID: " + id));

        conta.setNome(dto.nome());
        conta.setTipo(dto.tipo());
        conta.setSaldo(dto.saldo());
        conta.setAtualizadoEm(java.time.LocalDateTime.now());

        Conta updated = repositorioConta.save(conta);
        return ContaMapper.toDto(updated);
    }

    public void deleteConta(UUID id) {
        if (!repositorioConta.existsById(id)) {
            throw new RuntimeException("Conta não encontrada com o ID: " + id);
        }
        repositorioConta.deleteById(id);
    }
}
