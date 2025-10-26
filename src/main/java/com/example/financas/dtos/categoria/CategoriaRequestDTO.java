package com.example.financas.dtos.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequestDTO(
        @NotBlank(message = "O nome da categoria é obrigatório")
        @Size(min = 3, max = 50, message = "O nome da categoria deve ter entre 3 e 50 caracteres")
        String nome,

        @NotBlank(message = "O tipo da categoria é obrigatório")
        @Size(min = 3, max = 50, message = "O tipo da categoria deve ter entre 3 e 50 caracteres")
        String tipo
) {

}
