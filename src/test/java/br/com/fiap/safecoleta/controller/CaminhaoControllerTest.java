package br.com.fiap.safecoleta.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.fiap.safecoleta.model.Caminhao;
import br.com.fiap.safecoleta.service.CaminhaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CaminhaoController.class)
public class CaminhaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CaminhaoService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Caminhao caminhao;

    @BeforeEach
    public void setup() {
        caminhao = new Caminhao();
        caminhao.setId(1L);
        caminhao.setPlaca("ABC1234");
    }

    @Test
    public void testCadastroCaminhao() throws Exception {
        when(service.cadastroCaminhao(any(Caminhao.class))).thenReturn(caminhao);

        ResultActions response = mockMvc.perform(post("/api/caminhao/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(caminhao)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(caminhao.getId()))
                .andExpect(jsonPath("$.placa").value(caminhao.getPlaca()));

        verify(service).cadastroCaminhao(any(Caminhao.class));
    }

    @Test
    public void testConsultarCaminhaoPorId() throws Exception {
        Long id = 1L;
        when(service.consultarCaminhaoPorId(id)).thenReturn(caminhao);

        ResultActions response = mockMvc.perform(get("/api/caminhao/id/{id}", id)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(caminhao.getId()))
                .andExpect(jsonPath("$.placa").value(caminhao.getPlaca()));

        verify(service).consultarCaminhaoPorId(id);
    }
}