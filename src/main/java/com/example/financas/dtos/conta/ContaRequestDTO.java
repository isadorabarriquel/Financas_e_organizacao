package com.example.financas.dtos.conta;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PositiveOrZero;

public record ContaRequestDTO(
        @NotBlank(message = "O nome da conta é obrigatório")
        @Size(min = 3, max = 50, message = "O nome da conta deve ter entre 3 e 50 caracteres")
        String nome,

        @NotBlank(message = "O tipo da conta é obrigatório")
        @Size(min = 3, max = 50, message = "O tipo da conta deve ter entre 3 e 50 caracteres")
        String tipo,

        @PositiveOrZero(message = "O saldo inicial não pode ser negativo")
        Double saldo
) {

}
