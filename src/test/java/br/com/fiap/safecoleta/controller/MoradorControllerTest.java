package br.com.fiap.safecoleta.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.fiap.safecoleta.model.Morador;
import br.com.fiap.safecoleta.service.MoradorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MoradorController.class)
public class MoradorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MoradorService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Morador morador;

    @BeforeEach
    public void setup() {
        morador = new Morador();
        morador.setId(1L);
        morador.setNome("João");
    }

    @Test
    public void testCadastroMorador() throws Exception {
        when(service.cadastroMorador(any(Morador.class))).thenReturn(morador);

        ResultActions response = mockMvc.perform(post("/api/morador/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(morador)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(morador.getId()))
                .andExpect(jsonPath("$.nome").value(morador.getNome()));

        verify(service).cadastroMorador(any(Morador.class));
    }

    @Test
    public void testConsultarMoradorPorId() throws Exception {
        Long id = 1L;
        when(service.consultarMoradorPorId(id)).thenReturn(morador);

        ResultActions response = mockMvc.perform(get("/api/morador/id/{id}", id)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(morador.getId()))
                .andExpect(jsonPath("$.nome").value(morador.getNome()));

        verify(service).consultarMoradorPorId(id);
    }
}