package br.com.softplan.pessoas.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.softplan.pessoas.common.Genero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pessoa")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pessoa extends BaseModel {

    @Column(name = "nome", length = 45, nullable = false)
    private String nome;

    @Column(name = "genero", nullable = false)
    private Genero genero;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "naturalidade", nullable = true)
    private String naturalidade;

    @Column(name = "nacionalidade", nullable = true)
    private String nacionalidade;

    @Column(nullable = false, unique = true)
    private String cpf;

    /* Relacionamentos */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_endereco", referencedColumnName = "id")
    private Endereco endereco;

}
