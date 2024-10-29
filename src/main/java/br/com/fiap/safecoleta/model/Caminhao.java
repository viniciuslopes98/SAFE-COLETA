package br.com.fiap.safecoleta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="tbl_caminhao")
public class Caminhao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Placa do caminhão é obrigatória!")
    @Size(min = 7, max = 7, message = "O tamanho da Placa é inválido! Deve conter todos o 7 caracteres. ")
    @Column(name = "placa", nullable = false)
    private String placa;

    private String motorista;

    private String status;

    @Column(name = "ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao;


}
