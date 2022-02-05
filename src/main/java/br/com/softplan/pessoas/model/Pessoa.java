package br.com.softplan.pessoas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import br.com.softplan.pessoas.common.Sexo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pessoa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 45, nullable = false)
    private String nome;

    @Column(name = "sexo", nullable = false)
    private Sexo sexo;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "naturalidade", nullable = false)
    private String naturalidade;

    @Column(name = "nacionalidade", nullable = false)    
    private String nacionalidade;
    
    @Column(nullable = false, unique = true)
    private String cpf;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date lastUpdateDate;

}
