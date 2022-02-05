package br.com.softplan.pessoas.dto;

import java.util.Date;

import br.com.softplan.pessoas.common.Sexo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {
    
    private Long id;
    private String nome;
    private Sexo sexo;
    private String email;
    private Date dataNascimento;
    private String naturalidade;
    private String nacionalidade;
    private String cpf;

}
