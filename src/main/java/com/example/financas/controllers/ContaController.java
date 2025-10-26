package com.example.financas.controllers;

import com.example.financas.dtos.conta.ContaRequestDTO;
import com.example.financas.dtos.conta.ContaResponseDTO;
import com.example.financas.services.ContaService;
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
    public List<ContaResponseDTO> getAllContas(
            @RequestParam(defaultValue = "0") int paginaAtual,
            @RequestParam(defaultValue = "10") int tamanhoPagina
    ) {
        return contaService.getAllContas(paginaAtual, tamanhoPagina);
    }

    @GetMapping("/{id}")
    public ContaResponseDTO getContaPorId(@PathVariable UUID id){
        return contaService.getContaPorId(id);
    }

    @PostMapping
    public ContaResponseDTO createConta(@RequestBody ContaRequestDTO contaRequestDTO){
        return contaService.createConta(contaRequestDTO);
    }

    @PutMapping("/{id}")
    public ContaResponseDTO updateConta(@PathVariable UUID id, @RequestBody ContaRequestDTO contaRequestDTO) {
        return contaService.updateConta(id, contaRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteConta(@PathVariable UUID id) {
        contaService.deleteConta(id);
    }
}


