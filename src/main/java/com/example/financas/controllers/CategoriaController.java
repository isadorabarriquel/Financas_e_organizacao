package com.example.financas.controllers;

import com.example.financas.dtos.categoria.CategoriaRequestDTO;
import com.example.financas.dtos.categoria.CategoriaResponseDTO;
import com.example.financas.services.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<CategoriaResponseDTO> getAllCategorias(
            @RequestParam(defaultValue = "0") int paginaAtual,
            @RequestParam(defaultValue = "10") int tamanhoPagina,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String tipo
    ){
        return categoriaService.getAllCategorias(paginaAtual, tamanhoPagina, nome, tipo);
    }

    @PostMapping
    public CategoriaResponseDTO createCategoria(@RequestBody CategoriaRequestDTO dto){
        return categoriaService.createCategoria(dto);
    }

    @PutMapping("/{id}")
    public CategoriaResponseDTO updateCategoria(@PathVariable UUID id, @RequestBody CategoriaRequestDTO dto){
     return categoriaService.updateCategoria(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable UUID id) {
        categoriaService.deleteCategoria(id);
    }
}
