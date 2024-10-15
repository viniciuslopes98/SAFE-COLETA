package br.com.fiap.safecoleta.controller;

import br.com.fiap.safecoleta.dto.AtualizarAgendamentoDTO;
import br.com.fiap.safecoleta.dto.CadastrarAgendamentoDTO;
import br.com.fiap.safecoleta.model.AgendamentoColeta;
import br.com.fiap.safecoleta.service.AgendamentoColetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agendamento")
@Tag(name = "Agendamento", description = "Endpoints para agendamentos de coleta")
public class AgendamentoColetaController {

    @Autowired
    public AgendamentoColetaService service;


    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar um novo agendamento de coleta")
    public AgendamentoColeta cadastroColeta (@RequestBody @Valid CadastrarAgendamentoDTO cadastrarAgendamentoDTO){
        return service.agendamentoColeta(cadastrarAgendamentoDTO);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AgendamentoColeta consultaAgendamento(@PathVariable Long id){
        return service.consultaAgendamentoPorId(id);
    }

    @GetMapping("/agendamentos")
    @ResponseStatus(HttpStatus.OK)
    public Page<AgendamentoColeta> listarTodosOsContatos(Pageable paginacao){
        return service.listarTodosOsAgendamentos(paginacao);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirAgendamento(@PathVariable Long id) {
        service.excluirAgendamentoPorId(id);
    }

    @PutMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AgendamentoColeta atualizarAgendamento(@PathVariable Long id, @RequestBody @Valid AtualizarAgendamentoDTO atualizarAgendamentoDTO) {
        return service.atualizarAgendamento(id, atualizarAgendamentoDTO);
    }
}
