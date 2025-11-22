package com.example.financas.controllers;

import com.example.financas.dtos.categoria.CategoriaRequestDTO;
import com.example.financas.dtos.categoria.CategoriaResponseDTO;
import com.example.financas.services.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CategoriaResponseDTO>> getAllCategorias(
            @RequestParam(defaultValue = "0") int paginaAtual,
            @RequestParam(defaultValue = "10") int tamanhoPagina,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String tipo
    ){
        List<CategoriaResponseDTO> categorias =
                categoriaService.getAllCategorias(paginaAtual, tamanhoPagina, nome, tipo);

        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> createCategoria(@RequestBody CategoriaRequestDTO dto){
        CategoriaResponseDTO categoriaCriada = categoriaService.createCategoria(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoriaCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> updateCategoria(@PathVariable UUID id, @RequestBody CategoriaRequestDTO dto){
        CategoriaResponseDTO categoriaAtualizada = categoriaService.updateCategoria(id, dto);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable UUID id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
