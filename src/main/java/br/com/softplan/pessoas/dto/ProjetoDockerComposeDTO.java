package br.com.softplan.pessoas.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProjetoDockerComposeDTO {
    
    private String nome;
    private String descricao;
    private List<String> urls;
    private String comando;

}
