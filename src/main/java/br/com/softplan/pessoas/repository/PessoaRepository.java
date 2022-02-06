package br.com.softplan.pessoas.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.softplan.pessoas.model.Pessoa;

public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long>{
    
    // @Query("FROM Pessoa p WHERE LOWER(p.nome) like %:searchTerm% OR LOWER(p.email) like %:searchTerm%")
    // Page<Pessoa> search( @Param("searchTerm") String searchTerm, Pageable pageable);
    
}
