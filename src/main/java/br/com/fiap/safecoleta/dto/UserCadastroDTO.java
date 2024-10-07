package br.com.fiap.safecoleta.dto;

import br.com.fiap.safecoleta.model.PapeisDoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCadastroDTO(

        Long usuarioId,

        @NotBlank(message = "O nome do usuario é obrigatorio")
        String nome,

        @NotBlank(message = "O e-mail do usuario é obrigatório!")
        @Email(message = "O e-mail do usuario não é válido!")
        String email,

        @NotBlank(message = "A senha é obrigatório")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 a 20 caracteres.")
        String senha,


        PapeisDoUsuario papelUsuario

) {
}
