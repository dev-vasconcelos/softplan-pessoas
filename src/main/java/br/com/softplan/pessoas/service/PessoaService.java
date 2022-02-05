package br.com.softplan.pessoas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
        
        pessoa.setNome(pessoaRequest.getNome());
        pessoa.setSexo(pessoaRequest.getSexo());
        pessoa.setEmail(pessoaRequest.getNaturalidade());
        pessoa.setDataNascimento(pessoaRequest.getDataNascimento());
        pessoa.setNaturalidade(pessoaRequest.getNaturalidade());
        pessoa.setNacionalidade(pessoaRequest.getNacionalidade());
        pessoa.setCpf(pessoaRequest.getCpf());

        return repository.save(pessoa);
    }

    public List<Pessoa> findAll() {
            return repository.findAll();
    }

    public Pessoa findById(long id) {
        Optional<Pessoa> result = repository.findById(id);
        if(result.isPresent()) {
                return result.get();
        }else {
                throw new ResourceNotFoundException("Pessoa não encontrada");
        }
    }

    public void delete(long id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));

        repository.delete(pessoa);    
    }       
}