package br.com.fiap.safecoleta.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.fiap.safecoleta.dto.CadastrarNotificacaoDTO;
import br.com.fiap.safecoleta.model.Notificacao;
import br.com.fiap.safecoleta.service.NotificacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(NotificacaoController.class)
public class NotificacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificacaoService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Notificacao notificacao;
    private CadastrarNotificacaoDTO cadastrarNotificacaoDTO;

    @BeforeEach
    public void setup() {
        notificacao = new Notificacao();
        notificacao.setId(1L);
        notificacao.setMensagem("Nova Notificação");

        cadastrarNotificacaoDTO = new CadastrarNotificacaoDTO();
        cadastrarNotificacaoDTO.setMensagem("Nova Notificação");
    }


    @Test
    public void testConsultarNotificacaoPorId() throws Exception {
        Long id = 1L;
        when(service.cosultarNotificacaoPorId(id)).thenReturn(notificacao);

        ResultActions response = mockMvc.perform(get("/api/notificacao/id/{id}", id)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(notificacao.getId()))
                .andExpect(jsonPath("$.mensagem").value(notificacao.getMensagem()));

        verify(service).cosultarNotificacaoPorId(id);
    }
}