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
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "CAMINHAO_SEQ"
    )
    @SequenceGenerator(
            name = "CAMINHAO_SEQ",
            sequenceName = "CAMINHAO_SEQ",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "placa", nullable = false)
    private String placa;
    private String motorista;
    private String status;
    @Column(name = "ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao;


}
