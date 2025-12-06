package com.example.financas.dtos.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record UsuarioRequestDTO(
        @NotBlank(message = "Nome não pode ser nulo")
        @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
        String nome,

        @NotBlank(message = "E-mail obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, max = 16, message = "A senha deve ter entre 6 e 16 caracteres")
        String senha
) {
}
