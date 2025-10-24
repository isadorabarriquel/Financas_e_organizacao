package com.example.financas.services;

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

@Service
public class ContaService {
    private final RepositorioConta repositorioConta;

    public ContaService(
            RepositorioConta repositorioConta
    ) {
        this.repositorioConta = repositorioConta;
    }

    public static List<ContaResponseDTO> getAllContas(int paginaAtual, int tamanhoPagina) {
        Pageable pageable = PageRequest.of(paginaAtual, tamanhoPagina, Sort.by("nome"));
        Page<Conta> page = repositorioConta.findAll(pageable);
        return page.stream().map(ContaMapper::toDto).toList();
    }

}
