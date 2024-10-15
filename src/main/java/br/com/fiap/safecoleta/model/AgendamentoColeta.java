package br.com.fiap.safecoleta.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="tbl_agendamento_coleta")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AgendamentoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Caminhao caminhao;

    private String tipoResiduos;


    private LocalDate dataAgendamento;

    private String horario;

    private String endereco;

    private Boolean confirmado;

    public AgendamentoColeta(AgendamentoColeta agendamentoColeta) {
    }
}
