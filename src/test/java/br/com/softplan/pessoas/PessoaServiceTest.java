package br.com.softplan.pessoas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.softplan.pessoas.common.Genero;
import br.com.softplan.pessoas.model.Endereco;
import br.com.softplan.pessoas.model.Pessoa;
import br.com.softplan.pessoas.service.PessoaService;

@SpringBootTest
@ActiveProfiles("test")
public class PessoaServiceTest {
    
    @Autowired
    private PessoaService pessoaService;

    @Test
    public void adicionarPessoa() {
        Endereco enderecoFalso = new Endereco("85853000", "rua que existe", "complemento que existe", 98, "bairro que existe", "Brasil", "Paraná", "Foz do Iguaçu", null);
        Pessoa pessoa = pessoaService.save(new Pessoa("Vini Ivo", Genero.MASCULINO, "eu@viniivo.com", new Date(), "Paranaense", "Brasil", "26634325049", enderecoFalso));
        Pessoa foundPessoa = pessoaService.findById(pessoa.getId());

        assertNotNull(foundPessoa);
        assertEquals(pessoa.getNome(), foundPessoa.getNome());
    }

    @Test
    public void removerPessoa() {
        Endereco enderecoFalso = new Endereco("85853000", "rua que existe", "complemento que existe", 98, "bairro que existe", "Brasil", "Paraná", "Foz do Iguaçu", null);
        Pessoa pessoa = pessoaService.save(new Pessoa("Ivo Vini", Genero.MASCULINO, "eu@ivovini.com", new Date(), "Paranaense", "Brasil", "72009090098", enderecoFalso));
        Pessoa foundPessoa = pessoaService.findById(pessoa.getId());

        assertNotNull(foundPessoa);
        pessoaService.delete(pessoa.getId());
    }

}
