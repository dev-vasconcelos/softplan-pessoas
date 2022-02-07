package br.com.softplan.pessoas;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import java.util.Date;

import br.com.softplan.pessoas.common.Genero;
import br.com.softplan.pessoas.model.Pessoa;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PessoaV1ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", password = "{noop}password", roles = "USER")
    void givenPessoaShouldReturnCreated() throws Exception {
        Pessoa pessoa = new Pessoa("Pedro Vasconcelos", Genero.MASCULINO, "eu@pedroivovasconcelos.com", new Date(), "Paranaense", "Brasil", "89382623086", null);
        mockMvc.perform(post("/v1/pessoa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "admin", password = "{noop}password", roles = "USER")
    void givenPessoaWithInvalidCPFShouldReturnException() throws Exception {
        Pessoa pessoa = new Pessoa("Regina Vasconcelos", Genero.MASCULINO, "eu@reginavasconcelos.com", new Date(), "Paranaense", "Brasil", "11111111111", null);

        mockMvc.perform(post("/v1/pessoa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "admin", password = "{noop}password", roles = "USER")
    void givenPessoaWithInvalidDataNascimentoShouldReturnException() throws Exception {
        Pessoa pessoa = new Pessoa("Pessoa Vasconcelos", Genero.MASCULINO, "eu@pessoavasconcelos.com", new Date(-2208988799999L), "Paranaense", "Brasil", "28244202009", null);

        mockMvc.perform(post("/v1/pessoa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().is4xxClientError());
                
    }

    @Test
    @WithMockUser(username = "admin", password = "{noop}password", roles = "USER")
    void givenDuplicatedPessoaShouldReturnException() throws Exception {
        Pessoa pessoa = new Pessoa("Jai Vasconcelos", Genero.MASCULINO, "eu@jaivasconcelos.com", new Date(), "Paranaense", "Brasil", "89943341068", null);

        mockMvc.perform(post("/v1/pessoa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().is2xxSuccessful());
        
        mockMvc.perform(post("/v1/pessoa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @WithMockUser(username = "naoecerto", password = "{noop}senhaerrada", roles = "USER")
    void givenWrongCredentialsShouldReturnUnauthorized() throws Exception {
        Pessoa pessoa = new Pessoa("Pedro Ivo", Genero.MASCULINO, "eu@pedroivo.com", new Date(), "Paranaense", "Brasil", "11111111111", null);

        mockMvc.perform(post("/v1/pessoa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "admin", password = "{noop}password", roles = "USER")
    void givenNoParametersShouldReturnOk() throws Exception {
        ResultActions andExpect = mockMvc.perform(get("/v1/pessoa")).andDo(print()).andExpect(status().is2xxSuccessful());
  
        
        assertNotNull(andExpect);
    }

    
}
