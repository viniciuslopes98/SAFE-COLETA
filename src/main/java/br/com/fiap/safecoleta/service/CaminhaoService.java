package br.com.fiap.safecoleta.service;

import br.com.fiap.safecoleta.exception.CaminhaoNaoEncontradoException;
import br.com.fiap.safecoleta.model.Caminhao;
import br.com.fiap.safecoleta.repository.CaminhaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaminhaoService {

    @Autowired
    public CaminhaoRepository repository;

    //método para cadastrar um caminhão
    public Caminhao cadastroCaminhao(Caminhao caminhao){
        return repository.save(caminhao);

    }

    //método para consultar caminhão por Id
    public Caminhao consultarCaminhaoPorId(Long id){
        var resultadoBusca = repository.findById(id);
        if(resultadoBusca.isPresent()){
            return resultadoBusca.get();
        }else throw new CaminhaoNaoEncontradoException("Caminhão não encontrado!");
    }

}
