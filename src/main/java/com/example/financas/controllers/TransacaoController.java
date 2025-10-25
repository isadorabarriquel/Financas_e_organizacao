package com.example.financas.controllers;

import com.example.financas.dtos.transacao.TransacaoRequestDTO;
import com.example.financas.dtos.transacao.TransacaoResponseDTO;
import com.example.financas.services.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;
    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public List<TransacaoResponseDTO> getAllTransacoes(
            @RequestParam(defaultValue = "0") int paginaAtual,
            @RequestParam(defaultValue = "10") int tamanhoPagina
    ) {
        return transacaoService.getAllTransacoes(paginaAtual, tamanhoPagina);
    }

    @PostMapping
    public TransacaoResponseDTO criar(@RequestBody @Valid TransacaoRequestDTO dto) {
        return transacaoService.criar(dto);
    }
}
