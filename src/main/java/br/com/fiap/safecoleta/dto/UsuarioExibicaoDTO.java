package br.com.fiap.safecoleta.dto;

import br.com.fiap.safecoleta.model.PapeisDoUsuario;
import br.com.fiap.safecoleta.model.User;

public record UsuarioExibicaoDTO(Long usuarioId, String nome, String email, PapeisDoUsuario papelUsuario) {

    public UsuarioExibicaoDTO(User userSalvo) {
    this(
            userSalvo.getUsuarioId(), userSalvo.getNome(), userSalvo.getEmail(), userSalvo.getPapelUsuario()
    );
    }

}
