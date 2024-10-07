package br.com.fiap.safecoleta.service;

import br.com.fiap.safecoleta.exception.MoradorNaoEncontradoException;
import br.com.fiap.safecoleta.model.Morador;
import br.com.fiap.safecoleta.repository.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoradorService {

    @Autowired
    public MoradorRepository repository;


    public Morador cadastroMorador(Morador morador) {
        return repository.save(morador);

    }


    public Morador consultarMoradorPorId(Long id) {
        var resultadoBusca = repository.findById(id);
        if (resultadoBusca.isPresent()) {
            return resultadoBusca.get();
        } else throw new MoradorNaoEncontradoException("Morador não encontrado!");
    }


}
