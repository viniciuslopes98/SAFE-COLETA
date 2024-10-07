package br.com.fiap.safecoleta.controller;

import br.com.fiap.safecoleta.config.security.TokenService;
import br.com.fiap.safecoleta.dto.LoginDTO;
import br.com.fiap.safecoleta.dto.TokenDTO;
import br.com.fiap.safecoleta.dto.UserCadastroDTO;
import br.com.fiap.safecoleta.dto.UsuarioExibicaoDTO;
import br.com.fiap.safecoleta.model.User;
import br.com.fiap.safecoleta.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService service;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.email()
                        , loginDTO.senha()
                );
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.gerarToken((User)auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO registrar(@RequestBody @Valid UserCadastroDTO userCadastroDTO) {
        UsuarioExibicaoDTO usuarioSalvo = null;
        usuarioSalvo = service.salvarUsuario(userCadastroDTO);
        return usuarioSalvo;
    }


}
