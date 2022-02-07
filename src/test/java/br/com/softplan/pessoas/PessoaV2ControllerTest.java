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
import br.com.softplan.pessoas.model.Endereco;
import br.com.softplan.pessoas.model.Pessoa;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PessoaV2ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", password = "{noop}password", roles = "USER")
    void givenPessoaShouldReturnCreated() throws Exception {
        Endereco enderecoFalso = new Endereco("85853000", "rua que existe", "complemento que existe", 98, "bairro que existe", "Brasil", "Paraná", "Foz do Iguaçu", null);
        Pessoa pessoa = new Pessoa("Pedro Ivo", Genero.MASCULINO, "eu@pedroivo.com", new Date(), "Paranaense", "Brasil", "06639488000", enderecoFalso);

        mockMvc.perform(post("/v2/pessoa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "admin", password = "{noop}password", roles = "USER")
    void givenPessoaWithInvalidCPFShouldReturnException() throws Exception {
        Endereco enderecoFalso = new Endereco("85853000", "rua que existe", "complemento que existe", 98, "bairro que existe", "Brasil", "Paraná", "Foz do Iguaçu", null);
        Pessoa pessoa = new Pessoa("Ivo Pedro", Genero.MASCULINO, "eu@ivopedro.com", new Date(), "Paranaense", "Brasil", "11111111111", enderecoFalso);

        mockMvc.perform(post("/v2/pessoa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "admin", password = "{noop}password", roles = "USER")
    void givenPessoaWithInvalidDataNascimentoShouldReturnException() throws Exception {
        Endereco enderecoFalso = new Endereco("85853000", "rua que existe", "complemento que existe", 98, "bairro que existe", "Brasil", "Paraná", "Foz do Iguaçu", null);
        Pessoa pessoa = new Pessoa("Nome Sobrenome", Genero.MASCULINO, "eu@nomesobrenome.com", new Date(-2208988799999L), "Paranaense", "Brasil", "06639488000", enderecoFalso);

        mockMvc.perform(post("/v2/pessoa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().is4xxClientError());
                
    }

    @Test
    @WithMockUser(username = "admin", password = "{noop}password", roles = "USER")
    void givenDuplicatedPessoaShouldReturnException() throws Exception {
        Endereco enderecoFalso = new Endereco("85853000", "rua que existe", "complemento que existe", 98, "bairro que existe", "Brasil", "Paraná", "Foz do Iguaçu", null);
        Pessoa pessoa = new Pessoa("Sobrenome Nome", Genero.MASCULINO, "eu@sobrenomenome.com", new Date(), "Paranaense", "Brasil", "09787887020", enderecoFalso);

        mockMvc.perform(post("/v2/pessoa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().is2xxSuccessful());
        
        mockMvc.perform(post("/v2/pessoa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @WithMockUser(username = "naoecerto", password = "{noop}senhaerrada", roles = "USER")
    void givenWrongCredentialsShouldReturnUnauthorized() throws Exception {
        Endereco enderecoFalso = new Endereco("85853000", "rua que existe", "complemento que existe", 98, "bairro que existe", "Brasil", "Paraná", "Foz do Iguaçu", null);
        Pessoa pessoa = new Pessoa("Nome Completo", Genero.MASCULINO, "eu@nomecompleto.com", new Date(), "Paranaense", "Brasil", "11111111111", enderecoFalso);

        mockMvc.perform(post("/v2/pessoa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "admin", password = "{noop}password", roles = "USER")
    void givenNoParametersShouldReturnOk() throws Exception {
        ResultActions andExpect = mockMvc.perform(get("/v2/pessoa")).andDo(print()).andExpect(status().is2xxSuccessful());
  
        
        assertNotNull(andExpect);
    }

    
}
