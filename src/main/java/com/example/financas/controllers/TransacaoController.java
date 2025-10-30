package com.example.financas.controllers;

import com.example.financas.dtos.conta.ContaRequestDTO;
import com.example.financas.dtos.conta.ContaResponseDTO;
import com.example.financas.dtos.transacao.TransacaoRequestDTO;
import com.example.financas.dtos.transacao.TransacaoResponseDTO;
import com.example.financas.services.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public TransacaoResponseDTO getTransacaoPorId(@PathVariable UUID id){
        return transacaoService.getTransacaoPorId(id);
    }

    @PostMapping
    public TransacaoResponseDTO criar(@RequestBody @Valid TransacaoRequestDTO dto) {
        return transacaoService.createTransacao(dto);
    }

    @PutMapping("/{id}")
    public TransacaoResponseDTO updateTransacao(@PathVariable UUID id, @RequestBody TransacaoRequestDTO dto) {
        return transacaoService.updateTransacao(id, dto);
    }

}
