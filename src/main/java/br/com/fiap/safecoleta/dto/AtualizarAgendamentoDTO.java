package br.com.fiap.safecoleta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class AtualizarAgendamentoDTO {

    @NotNull(message = "Id do Caminhão para coleta é obrigatório!")
    private Long caminhaoId;

    @NotBlank(message = "Tipo de resíduo é obrigatório!")
    @Size(min = 3, max = 20, message = "O tamanho do Nome é inválido! Mínimo 3 e Máximo 20 caracteres.")
    private String tipoResiduos;

    @NotNull(message = "A data não pode ser nula!")
    private LocalDate dataAgendamento;

    @NotBlank(message = "O horário do Agendamento não pode ser nulo!")
    private String horario;

    @NotBlank(message = "O endereço para Agendamento não pode ser nulo!")
    private String endereco;

    @NotNull(message = "Confirme o status do Agendamento!")
    private Boolean confirmado;

}
