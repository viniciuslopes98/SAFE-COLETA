package br.com.fiap.safecoleta.service;

import br.com.fiap.safecoleta.dto.UserCadastroDTO;
import br.com.fiap.safecoleta.dto.UsuarioExibicaoDTO;
import br.com.fiap.safecoleta.model.User;
import br.com.fiap.safecoleta.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    public UsuarioExibicaoDTO salvarUsuario(UserCadastroDTO userCadastroDTO) {

        String senhaCriptografada = new BCryptPasswordEncoder().encode(userCadastroDTO.senha());

        User user = new User();
        BeanUtils.copyProperties(userCadastroDTO, user);
        user.setSenha(senhaCriptografada);

        User userSalvo = repository.save(user);
        return new UsuarioExibicaoDTO(userSalvo);

    }
}
