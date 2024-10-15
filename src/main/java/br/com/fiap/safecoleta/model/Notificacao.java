package br.com.fiap.safecoleta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbl_notificacao")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Morador morador;

    private String mensagem;

    @Column(name = "data_notificacao")
    private LocalDateTime dataEnvio;

    private Boolean lida;


}
