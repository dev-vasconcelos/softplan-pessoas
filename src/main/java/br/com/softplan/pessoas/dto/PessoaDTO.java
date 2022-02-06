package br.com.softplan.pessoas.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.softplan.pessoas.common.Cpf;
import br.com.softplan.pessoas.common.DataNascimento;
import br.com.softplan.pessoas.common.Genero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {
    
    private Long id;

    @NotEmpty(message = "Nome não pode ser nulo")
    private String nome;

    @NotNull(message = "Campo gênero não pode ser nulo")
    private Genero genero;
    
    @Email(message = "Email inválido")
    private String email;
    
    @NotNull(message = "Data de nascimento não pode ser nulo")
    @DataNascimento
    private Date dataNascimento;
    
    private String naturalidade;

    private String nacionalidade;

    @Cpf
    @NotEmpty(message = "Cpf não pode ser nulo")
    private String cpf;

}
