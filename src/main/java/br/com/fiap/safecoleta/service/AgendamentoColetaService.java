package br.com.fiap.safecoleta.service;

import br.com.fiap.safecoleta.dto.AtualizarAgendamentoDTO;
import br.com.fiap.safecoleta.dto.CadastrarAgendamentoDTO;
import br.com.fiap.safecoleta.exception.AgendamentoNaoEncontradoException;
import br.com.fiap.safecoleta.exception.CaminhaoNaoEncontradoException;
import br.com.fiap.safecoleta.model.AgendamentoColeta;
import br.com.fiap.safecoleta.model.Caminhao;
import br.com.fiap.safecoleta.repository.AgendamentoColetaRepository;
import br.com.fiap.safecoleta.repository.CaminhaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgendamentoColetaService {

    @Autowired
    public AgendamentoColetaRepository repository;

    @Autowired
    public CaminhaoRepository repositoryCaminhao;


    public AgendamentoColeta agendamentoColeta(CadastrarAgendamentoDTO cadastrarAgendamentoDTO) {
        Long caminhaoId = cadastrarAgendamentoDTO.getCaminhaoId();
        Optional<Caminhao> caminhaoOptional = repositoryCaminhao.findById(caminhaoId);

        if (caminhaoOptional.isEmpty()) {
            throw new CaminhaoNaoEncontradoException("Caminhão com o ID " + caminhaoId + " não encontrado.");
        }
        Caminhao retornoObjeto = repositoryCaminhao.getById(cadastrarAgendamentoDTO.getCaminhaoId());
        return repository.save(
                AgendamentoColeta.builder()
                        .dataAgendamento(cadastrarAgendamentoDTO.getDataEnvio())
                        .tipoResiduos(cadastrarAgendamentoDTO.getTipoResiduos())
                        .horario(cadastrarAgendamentoDTO.getHorario())
                        .endereco(cadastrarAgendamentoDTO.getEndereco())
                        .confirmado(cadastrarAgendamentoDTO.getConfirmado())
                        .caminhao(Caminhao.builder()
                                .id(retornoObjeto.getId())
                                .placa(retornoObjeto.getPlaca())
                                .motorista(retornoObjeto.getMotorista())
                                .status(retornoObjeto.getStatus())
                                .ultimaAtualizacao(retornoObjeto.getUltimaAtualizacao())
                                .build())
                        .build());
    }


    public AgendamentoColeta consultaAgendamentoPorId(Long id) {
        var resultadoBusca = repository.findById(id);
        if (resultadoBusca.isPresent()) {
            return resultadoBusca.get();
        } else throw new AgendamentoNaoEncontradoException("Agendamento não encontrado!");
    }

    public Page<AgendamentoColeta> listarTodosOsAgendamentos(Pageable paginacao) {
        return repository.findAllWithCaminhao(paginacao);
    }

    public void excluirAgendamentoPorId(Long id) {
        AgendamentoColeta agendamento = repository.findById(id)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException("Agendamento não encontrado!"));
        repository.delete(agendamento);
    }

    public AgendamentoColeta atualizarAgendamento(Long id, AtualizarAgendamentoDTO atualizarAgendamentoDTO) {
        AgendamentoColeta agendamentoExistente = consultaAgendamentoPorId(id);

        agendamentoExistente.setTipoResiduos(atualizarAgendamentoDTO.getTipoResiduos());
        agendamentoExistente.setDataAgendamento(atualizarAgendamentoDTO.getDataAgendamento());
        agendamentoExistente.setHorario(atualizarAgendamentoDTO.getHorario());
        agendamentoExistente.setEndereco(atualizarAgendamentoDTO.getEndereco());
        agendamentoExistente.setConfirmado(atualizarAgendamentoDTO.getConfirmado());

        if (atualizarAgendamentoDTO.getCaminhaoId() != null) {
            Caminhao novoCaminhao = repositoryCaminhao.getById(atualizarAgendamentoDTO.getCaminhaoId());
            agendamentoExistente.setCaminhao(novoCaminhao);
        }

        return repository.save(agendamentoExistente);
    }

}
