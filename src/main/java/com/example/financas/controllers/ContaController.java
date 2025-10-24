package com.example.financas.controllers;

import com.example.financas.dtos.conta.ContaResponseDTO;
import com.example.financas.services.ContaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {
    @GetMapping
    public List<ContaResponseDTO> getAllContas(
            @RequestParam(defaultValue = "0") int paginaAtual,
            @RequestParam(defaultValue = "10") int tamanhoPagina
    ) {
        return ContaService.getAllContas(paginaAtual, tamanhoPagina);
    }

}
