package com.example.financas.controllers;

import com.example.financas.dtos.conta.ContaRequestDTO;
import com.example.financas.dtos.conta.ContaResponseDTO;
import com.example.financas.dtos.usuario.UsuarioResponseDTO;
import com.example.financas.services.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public ResponseEntity<List<ContaResponseDTO>> getAllContas(
            @RequestParam(defaultValue = "0") int paginaAtual,
            @RequestParam(defaultValue = "10") int tamanhoPagina,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String tipo
    ) {
        List<ContaResponseDTO> contas = contaService.getAllContas(paginaAtual, tamanhoPagina, nome, tipo);
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> getContaPorId(@PathVariable UUID id){
        ContaResponseDTO conta =  contaService.getContaPorId(id);
        return ResponseEntity.ok(conta);
    }

    @PostMapping
    public ResponseEntity<ContaResponseDTO> createConta(@RequestBody ContaRequestDTO contaRequestDTO){
        ContaResponseDTO contaCriada =  contaService.createConta(contaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> updateConta(@PathVariable UUID id, @RequestBody ContaRequestDTO contaRequestDTO) {
        ContaResponseDTO contaAlterada = contaService.updateConta(id, contaRequestDTO);
        return ResponseEntity.ok(contaAlterada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable UUID id) {

        contaService.deleteConta(id);
        return ResponseEntity.noContent().build();
    }
}


