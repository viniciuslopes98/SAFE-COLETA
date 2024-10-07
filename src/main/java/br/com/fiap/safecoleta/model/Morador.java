package br.com.fiap.safecoleta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbl_morador")
public class Morador {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MORADOR_SEQ"
    )
    @SequenceGenerator(
            name = "MORADOR_SEQ",
            sequenceName = "MORADOR_SEQ",
            allocationSize = 1
    )
    private Long id;

    private String nome;

    private String email;


}