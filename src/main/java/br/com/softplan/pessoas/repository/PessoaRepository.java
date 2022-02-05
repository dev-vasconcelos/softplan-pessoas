package br.com.softplan.pessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.pessoas.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    
}
