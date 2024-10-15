package br.com.fiap.safecoleta.controller;

import static org.mockito.Mockito.*;  // Import para os métodos Mockito
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  // Métodos de requisição MockMvc
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;  // Métodos de verificação de resposta MockMvc

 // Serviço usado no Controller
import br.com.fiap.safecoleta.dto.AtualizarAgendamentoDTO;
import br.com.fiap.safecoleta.dto.CadastrarAgendamentoDTO;
import br.com.fiap.safecoleta.model.AgendamentoColeta;
import br.com.fiap.safecoleta.service.AgendamentoColetaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;

@WebMvcTest(AgendamentoColetaController.class)
public class AgendamentoColetaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgendamentoColetaService service;

    @Autowired
    private ObjectMapper objectMapper;

    private AgendamentoColeta agendamentoColeta;
    private CadastrarAgendamentoDTO cadastrarAgendamentoDTO;
    private AtualizarAgendamentoDTO atualizarAgendamentoDTO;

    @BeforeEach
    public void setup() {
        agendamentoColeta = new AgendamentoColeta();
        agendamentoColeta.setId(1L);
        agendamentoColeta.setTipoResiduos("Comum");

        cadastrarAgendamentoDTO = new CadastrarAgendamentoDTO();
        cadastrarAgendamentoDTO.setCaminhaoId(1L);
        cadastrarAgendamentoDTO.setTipoResiduos("Comum");

        atualizarAgendamentoDTO = new AtualizarAgendamentoDTO();
        atualizarAgendamentoDTO.setTipoResiduos("Perigoso");
    }


    @Test
    public void testConsultaAgendamento() throws Exception {
        Long id = 1L;
        when(service.consultaAgendamentoPorId(id)).thenReturn(agendamentoColeta);

        ResultActions response = mockMvc.perform(get("/api/agendamento/id/{id}", id)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(agendamentoColeta.getId()))
                .andExpect(jsonPath("$.tipoResiduos").value(agendamentoColeta.getTipoResiduos()));

        verify(service).consultaAgendamentoPorId(id);
    }

    @Test
    public void testListarTodosOsAgendamentos() throws Exception {
        Page<AgendamentoColeta> agendamentoPage = new PageImpl<>(Collections.singletonList(agendamentoColeta));
        when(service.listarTodosOsAgendamentos(any(PageRequest.class))).thenReturn(agendamentoPage);

        ResultActions response = mockMvc.perform(get("/api/agendamento/agendamentos")
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(agendamentoColeta.getId()));

        verify(service).listarTodosOsAgendamentos(any(PageRequest.class));
    }

    @Test
    public void testExcluirAgendamento() throws Exception {
        Long id = 1L;
        doNothing().when(service).excluirAgendamentoPorId(id);

        ResultActions response = mockMvc.perform(delete("/api/agendamento/id/{id}", id)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());

        verify(service).excluirAgendamentoPorId(id);
    }

}