package br.com.fiap.safecoleta.repository;

import br.com.fiap.safecoleta.model.AgendamentoColeta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AgendamentoColetaRepository extends JpaRepository<AgendamentoColeta, Long> {

    @Query("SELECT a FROM AgendamentoColeta a JOIN FETCH a.caminhao")
    Page<AgendamentoColeta> findAllWithCaminhao(Pageable pageable);

}
