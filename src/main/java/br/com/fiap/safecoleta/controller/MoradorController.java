package br.com.fiap.safecoleta.controller;

import br.com.fiap.safecoleta.model.Morador;
import br.com.fiap.safecoleta.service.MoradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/morador")
public class MoradorController {

    @Autowired
    public MoradorService service;


    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public Morador cadastroMorador(@RequestBody Morador morador) {
        return service.cadastroMorador(morador);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Morador consultarMoradorPorId(@PathVariable Long id) {
        return service.consultarMoradorPorId(id);
    }


}
