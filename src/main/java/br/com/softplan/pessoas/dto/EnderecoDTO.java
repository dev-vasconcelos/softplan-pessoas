package br.com.softplan.pessoas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
    private Long id;

    private String cep;
    
    private String rua;

    private String complemento;

    private int numero;
    
    private String bairro;
    
    private String pais;
    
    private String estado;

    private String cidade;
}
