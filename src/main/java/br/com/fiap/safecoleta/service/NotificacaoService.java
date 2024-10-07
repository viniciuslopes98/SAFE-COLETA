package br.com.fiap.safecoleta.service;

import br.com.fiap.safecoleta.dto.CadastrarNotificacaoDTO;
import br.com.fiap.safecoleta.exception.NotificacaoNaoEncontradaException;
import br.com.fiap.safecoleta.model.Morador;
import br.com.fiap.safecoleta.model.Notificacao;
import br.com.fiap.safecoleta.repository.MoradorRepository;
import br.com.fiap.safecoleta.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    @Autowired
    public NotificacaoRepository repository;

    @Autowired
    public MoradorRepository repositoryMorador;


    public Notificacao cadastroNotificao(CadastrarNotificacaoDTO cadastrarNotificacaoDTO) {
        Morador retornoObjeto = repositoryMorador.getById(cadastrarNotificacaoDTO.getMoradorId());
        return repository.save(
                Notificacao.builder()
                        .mensagem(cadastrarNotificacaoDTO.getMensagem())
                        .dataEnvio(cadastrarNotificacaoDTO.getDataNotificacao())
                        .lida(cadastrarNotificacaoDTO.getLida())
                        .morador(Morador.builder()
                                .id(retornoObjeto.getId())
                                .nome(retornoObjeto.getNome())
                                .email(retornoObjeto.getEmail())
                                .build())
                        .build());
    }


    public Notificacao cosultarNotificacaoPorId(Long id){
        var retornoConsulta = repository.findById(id);
        if(retornoConsulta.isPresent()){
            return retornoConsulta.get();
        }else throw new NotificacaoNaoEncontradaException("Notificação não encontrada!");
    }

}
