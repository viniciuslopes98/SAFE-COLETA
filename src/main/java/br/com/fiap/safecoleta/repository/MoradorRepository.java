package br.com.fiap.safecoleta.repository;

import br.com.fiap.safecoleta.model.Morador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoradorRepository extends JpaRepository<Morador, Long> {


}
