package br.com.softplan.pessoas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_endereco")
public class Endereco extends BaseModel {    

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private int numero;
    
    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String pais;
    
    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String cidade;

    /* Relacionamentos */
    @OneToOne(mappedBy = "endereco")
    private Pessoa pessoa;

}
