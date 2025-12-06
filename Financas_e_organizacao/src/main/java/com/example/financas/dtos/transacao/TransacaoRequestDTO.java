package com.example.financas.dtos.transacao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransacaoRequestDTO(

        @NotNull(message = "Usuário não pode ser nulo")
        UUID usuarioId,

        @NotNull(message = "Conta não pode ser nula")
        UUID contaId,

        @NotNull(message = "A categoria é obrigatória")
        UUID categoriaId,

        @NotBlank(message = "Tipo de transação obrigatório")
        @Size(min = 7, max = 12, message = "O tipo deve ter entre 7 e 12 caracteres")
        @Pattern(regexp = "RECEITA|DESPESA|TRANSFERENCIA", message = "O tipo deve ser RECEITA, DESPESA ou TRANSFERENCIA")
        String tipo,

        @NotNull(message = "Valor é obrigatório")
        @Positive(message = "Valor deve ser positivo")
        BigDecimal valor,

        @NotNull(message = "A data é obrigatória")
        LocalDateTime data,

        @Size(max = 100, message = "A descrição pode ter no máximo 100 caracteres")
        String descricao
) {}
