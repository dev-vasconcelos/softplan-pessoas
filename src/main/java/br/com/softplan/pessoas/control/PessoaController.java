package br.com.softplan.pessoas.control;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.pessoas.dto.PessoaDTO;
import br.com.softplan.pessoas.model.Pessoa;
import br.com.softplan.pessoas.service.PessoaService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping({"api/pessoa"})
public class PessoaController {
    @Autowired
    private PessoaService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody PessoaDTO pessoaDTO) {
        try {
            Pessoa pessoaRequest = modelMapper.map(pessoaDTO, Pessoa.class);

		    Pessoa pessoa = service.save(pessoaRequest);

		    PessoaDTO pessoaResponse = modelMapper.map(pessoa, PessoaDTO.class);

		    return new ResponseEntity<PessoaDTO>(pessoaResponse, HttpStatus.CREATED);
        } catch(InternalError | Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> update(@RequestBody PessoaDTO pessoaDTO, @PathVariable Long id) {
        try {
            Pessoa pessoaRequest = modelMapper.map(pessoaDTO, Pessoa.class);
            Pessoa pessoa = service.update(id, pessoaRequest);
            PessoaDTO pessoaResponse = modelMapper.map(pessoa, PessoaDTO.class);

    		return ResponseEntity.ok().body(pessoaResponse);
        } catch(InternalError | Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping(path = "", produces = "application/json", params = {"page","size","sortBy"})
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sortBy) {
        try {           
            List<Pessoa> findResult = service.findAll(page, size, sortBy);
            List<PessoaDTO> collection = findResult.stream().map(book -> modelMapper.map(book, PessoaDTO.class))
                .collect(Collectors.toList());

            return ResponseEntity.ok(collection);
        } catch (InternalError | Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
    }

    @GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<?> findById(@PathVariable("id") long id) {
		try {
			Pessoa pessoa = service.findById(id);
		    PessoaDTO pessoaResponse = modelMapper.map(pessoa, PessoaDTO.class);
		    return ResponseEntity.ok().body(pessoaResponse);
		} catch (InternalError | Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}

    @DeleteMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<?> deleteByID(@PathVariable("id") long id) {
		try {
			service.delete(id);
		    return ResponseEntity.ok(this.findAll(1, 10, "id"));
		} catch (InternalError | Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}
    
}