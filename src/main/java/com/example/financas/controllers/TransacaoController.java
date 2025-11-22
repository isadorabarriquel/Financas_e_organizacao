package com.example.financas.controllers;

import com.example.financas.dtos.transacao.TransacaoRequestDTO;
import com.example.financas.dtos.transacao.TransacaoResponseDTO;
import com.example.financas.services.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TransacaoResponseDTO>> getAllTransacoes(
            @RequestParam(defaultValue = "0") int paginaAtual,
            @RequestParam(defaultValue = "10") int tamanhoPagina,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) UUID contaId,
            @RequestParam(required = false) UUID categoriaId,
            @RequestParam(required = false) UUID usuarioId
    ) {
        List<TransacaoResponseDTO> transacoes =  transacaoService.getAllTransacoes(
                paginaAtual, tamanhoPagina, tipo, contaId, categoriaId, usuarioId);
        if (transacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(transacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> getTransacaoPorId(@PathVariable UUID id){
        TransacaoResponseDTO transacao = transacaoService.getTransacaoPorId(id);
        return ResponseEntity.ok(transacao);
    }

    @PostMapping
    public ResponseEntity<TransacaoResponseDTO> criar(@RequestBody @Valid TransacaoRequestDTO dto) {
        TransacaoResponseDTO transacaoCriada = transacaoService.createTransacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> updateTransacao(@PathVariable UUID id, @RequestBody TransacaoRequestDTO dto) {
        TransacaoResponseDTO transacaoAtualizada =  transacaoService.updateTransacao(id, dto);
        return ResponseEntity.ok(transacaoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransacao(@PathVariable UUID id) {

        transacaoService.deleteTransacao(id);
        return ResponseEntity.noContent().build();
    }

}
