package br.com.fiap.safecoleta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotificacaoNaoEncontradaException extends RuntimeException {

    public NotificacaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}




