package br.com.fiap.safecoleta.controller;

import br.com.fiap.safecoleta.dto.CadastrarNotificacaoDTO;
import br.com.fiap.safecoleta.model.Notificacao;
import br.com.fiap.safecoleta.service.NotificacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificacao")
public class NotificacaoController {

    @Autowired
    public NotificacaoService service;

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public br.com.fiap.safecoleta.model.Notificacao cadastroNotificacao (@RequestBody @Valid CadastrarNotificacaoDTO cadastrarNotificacaoDTO){
        return service.cadastroNotificao(cadastrarNotificacaoDTO);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Notificacao consultaNotificacao(@PathVariable Long id){
        return service.cosultarNotificacaoPorId(id);
    }


}
