package br.com.fiap.safecoleta.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class CadastroMoradorDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    private String nome;

    @Email(message = "O e-mail está com formato inválido!")
    private String email;


}
