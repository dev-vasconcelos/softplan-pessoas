package br.com.softplan.pessoas.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.softplan.pessoas.model.Pessoa;
import br.com.softplan.pessoas.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public Pessoa save(Pessoa pessoaRequest) {
        return repository.save(pessoaRequest);
    }

    public Pessoa update(long id, Pessoa pessoaRequest) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa com id " + id + " não encontrada"));

        pessoa.setNome(pessoaRequest.getNome());
        pessoa.setSexo(pessoaRequest.getSexo());
        pessoa.setEmail(pessoaRequest.getEmail());
        pessoa.setDataNascimento(pessoaRequest.getDataNascimento());
        pessoa.setNaturalidade(pessoaRequest.getNaturalidade());
        pessoa.setNacionalidade(pessoaRequest.getNacionalidade());
        pessoa.setCpf(pessoaRequest.getCpf());

        return repository.save(pessoa);
    }

    public void delete(long id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa com id " + id + " não encontrada"));

        repository.delete(pessoa);
    }

    public Pessoa findById(long id) {
        Optional<Pessoa> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("Pessoa com id " + id + " não encontrada");
        }
    }

    public List<Pessoa> findAll(Integer page, Integer size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));

        Page<Pessoa> pagedResult = repository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
            // return pagedResult;
        } else {
            return new ArrayList<Pessoa>();
            // return null;
        }
    }

}