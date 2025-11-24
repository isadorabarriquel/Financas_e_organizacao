package com.example.financas.dtos.conta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ContaRequestDTO(

        @NotBlank(message = "O nome da conta é obrigatório")
        @Size(min = 3, max = 50, message = "O nome da conta deve ter entre 3 e 50 caracteres")
        String nome,

        @NotBlank(message = "O tipo da conta é obrigatório")
        @Size(min = 3, max = 50, message = "O tipo da conta deve ter entre 3 e 50 caracteres")
        String tipo,

        @NotNull(message = "O saldo inicial é obrigatório")
        @PositiveOrZero(message = "O saldo inicial não pode ser negativo")
        BigDecimal saldo
) {}
