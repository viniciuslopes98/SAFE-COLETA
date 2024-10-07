package br.com.fiap.safecoleta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class CadastrarNotificacaoDTO {

    @NotNull(message = "Id do Morador para notificar é obrigatório!")
    private Long moradorId;

    @NotBlank(message = "Mensagem é obrigatória!")
    @Size(min = 3, max = 300, message = "O tamanho da mensagem excede o limite. Mínimo 3 e Máximo 300 caracteres!")
    private String mensagem;


    @NotNull(message = "A data não pode ser nula!")
    private LocalDateTime dataNotificacao;

    @NotNull(message = "Confirme se a mensagem já foi lida pelo morador!")
    private Boolean lida;


}
