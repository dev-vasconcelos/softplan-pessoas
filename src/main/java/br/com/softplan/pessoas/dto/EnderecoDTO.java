package br.com.softplan.pessoas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
    private Long id;

    private String rua;

    private int numero;
    
    private String bairro;
    
    private String pais;
    
    private String estado;

    private String cidade;
}
