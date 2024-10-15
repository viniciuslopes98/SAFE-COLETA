package br.com.fiap.safecoleta.model;

import jakarta.persistence.*;
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

    @Column(name = "placa", nullable = false)
    private String placa;

    private String motorista;

    private String status;

    @Column(name = "ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao;


}
