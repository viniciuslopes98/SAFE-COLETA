package br.com.fiap.safecoleta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class CadastroCaminhaoDTO {

    private Long id;

    @NotBlank(message = "Placa do caminhão é obrigatória!")
    @Size(min = 7, max = 7, message = "O tamanho da Placa é inválido! Deve conter todos o 7 caracteres. ")
    private String placa;

    @NotBlank(message = "Informe o nome do Motorista.")
    private String motorista;

    @NotBlank(message = "Status inválido! Informe se o motorista está Ativado ou Desativado para fazer as rotas.")
    private String status;

    @NotNull(message = "A data não pode ser nula!")
    private LocalDateTime ultimaAtualizacao;
}
