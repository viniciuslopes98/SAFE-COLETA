package br.com.fiap.safecoleta.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(

        @NotBlank(message = "E-mail do usuário é obrigatório!")
        @Email(message = "E-mail está com o formato inválido!")
        String email,

        @NotBlank(message = "Senha do usuário não pode ser vazia!")
        @Size(min = 6, max = 20, message = "Falha ao logar! A senha deve conter entre 6 a 20 caracteres.")
        String senha
) {
}
