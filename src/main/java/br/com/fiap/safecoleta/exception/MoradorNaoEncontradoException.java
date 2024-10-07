package br.com.fiap.safecoleta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MoradorNaoEncontradoException extends RuntimeException {

    public MoradorNaoEncontradoException(String mensagem) {
        super(mensagem);
    }


}
