package br.com.fiap.safecoleta.controller;

import br.com.fiap.safecoleta.model.Caminhao;
import br.com.fiap.safecoleta.service.CaminhaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/caminhao")
public class CaminhaoController {

    @Autowired
    public CaminhaoService service;

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public Caminhao cadastroCaminhao(@RequestBody Caminhao caminhao) {
        return service.cadastroCaminhao(caminhao);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Caminhao consultarCaminhaoPorId(@PathVariable Long id) {
        return service.consultarCaminhaoPorId(id);
    }

}
