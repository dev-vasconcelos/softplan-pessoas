package br.com.softplan.pessoas.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.softplan.pessoas.model.Endereco;

public interface EnderecoRepository extends PagingAndSortingRepository<Endereco, Long>{
    
}
