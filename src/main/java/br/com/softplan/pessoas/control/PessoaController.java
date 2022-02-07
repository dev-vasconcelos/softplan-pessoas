package br.com.softplan.pessoas.control;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import br.com.softplan.pessoas.dto.PessoaV1DTO;
import br.com.softplan.pessoas.dto.PessoaV2DTO;
import br.com.softplan.pessoas.model.Endereco;
import br.com.softplan.pessoas.model.Pessoa;
import br.com.softplan.pessoas.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@RestController
// @RequestMapping({ "/v1/pessoa" })
public class PessoaController {
    @Autowired
    private PessoaService service;

    @Autowired
    private ModelMapper modelMapper;

    /* VERSÃO 2 */
    @PostMapping(path = { "/v2/pessoa" }, produces = "application/json")
    @Operation(summary = "Adicionar Pessoa")
    public ResponseEntity<?> saveV2(@Valid @RequestBody PessoaV2DTO pessoaDTO) {
        try {
            Endereco enderecoRequest = modelMapper.map(pessoaDTO.getEndereco(), Endereco.class);
            Pessoa pessoaRequest = modelMapper.map(pessoaDTO, Pessoa.class);
            pessoaRequest.setEndereco(enderecoRequest);

            Pessoa pessoa = service.save(pessoaRequest);

            PessoaV2DTO pessoaResponse = modelMapper.map(pessoa, PessoaV2DTO.class);

            return new ResponseEntity<PessoaV2DTO>(pessoaResponse, HttpStatus.CREATED);
        } catch (ConstraintViolationException cve) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ID ou CPF duplicados");
        } catch (BadRequest br) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getMessage());
        } catch (InternalError | Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping(path = { "/v2/pessoa/{id}" }, produces = "application/json")
    @Operation(summary = "Atualizar Pessoa")
    public ResponseEntity<?> updateV2(@Valid @RequestBody PessoaV2DTO pessoaDTO, @PathVariable Long id) {
        try {
            Pessoa pessoaRequest = modelMapper.map(pessoaDTO, Pessoa.class);
            Pessoa pessoa = service.update(id, pessoaRequest);
            PessoaV2DTO pessoaResponse = modelMapper.map(pessoa, PessoaV2DTO.class);

            return ResponseEntity.ok().body(pessoaResponse);
        } catch (BadRequest br) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getMessage());
        } catch (InternalError | Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping(path = { "/v2/pessoa/" }, produces = "application/json")
    @Operation(summary = "Listar todas as pessoas")
    public ResponseEntity<?> findAllV2(@RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size,
            @RequestParam(defaultValue = "id", required = false) String sortBy) {
        try {
            List<Pessoa> findResult = service.findAll(page, size, sortBy);
            List<PessoaV2DTO> collection = findResult.stream().map(book -> modelMapper.map(book, PessoaV2DTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(collection);
        } catch (InternalError | Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping(path = { "/v2/pessoa/{id}" }, produces = "application/json")
    @Operation(summary = "Procurar pessoa por ID")
    public ResponseEntity<?> findByIdV2(@PathVariable("id") long id) {
        try {
            Pessoa pessoa = service.findById(id);
            PessoaV2DTO pessoaResponse = modelMapper.map(pessoa, PessoaV2DTO.class);
            return ResponseEntity.ok().body(pessoaResponse);
        } catch (BadRequest br) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getMessage());
        } catch (InternalError | Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping(path = "v2.0/{id}", produces = "application/json")
    @Operation(summary = "Deletar pessoa")
    public ResponseEntity<?> deleteByIDV2(@PathVariable("id") long id) {
        try {
            service.delete(id);
            return this.findAllV2(0, 10, "id");
        } catch (BadRequest br) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getMessage());
        } catch (InternalError | Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    /* VERSÃO 1 */

    @PostMapping(path = { "/v1/pessoa" }, produces = "application/json")
    @Operation(summary = "Adicionar Pessoa")
    public ResponseEntity<?> saveV1(@Valid @RequestBody PessoaV1DTO pessoaDTO) {
        try {
            Pessoa pessoaRequest = modelMapper.map(pessoaDTO, Pessoa.class);

            Pessoa pessoa = service.save(pessoaRequest);

            PessoaV1DTO pessoaResponse = modelMapper.map(pessoa, PessoaV1DTO.class);

            return new ResponseEntity<PessoaV1DTO>(pessoaResponse, HttpStatus.CREATED);
        } catch (ConstraintViolationException cve) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ID ou CPF duplicados");
        } catch (BadRequest br) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getMessage());
        } catch (InternalError | Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping(path = { "/v1/pessoa/{id}" }, produces = "application/json")
    @Operation(summary = "Atualizar Pessoa")
    public ResponseEntity<?> updateV1(@Valid @RequestBody PessoaV1DTO pessoaDTO, @PathVariable Long id) {
        try {
            Pessoa pessoaRequest = modelMapper.map(pessoaDTO, Pessoa.class);
            Pessoa pessoa = service.update(id, pessoaRequest);
            PessoaV1DTO pessoaResponse = modelMapper.map(pessoa, PessoaV1DTO.class);

            return ResponseEntity.ok().body(pessoaResponse);
        } catch (BadRequest br) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getMessage());
        } catch (InternalError | Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping(path = { "/v1/pessoa/" }, produces = "application/json")
    @Operation(summary = "Listar todas as pessoas")
    public ResponseEntity<?> findAllV1(@RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size,
            @RequestParam(defaultValue = "id", required = false) String sortBy) {
        try {
            List<Pessoa> findResult = service.findAll(page, size, sortBy);
            List<PessoaV1DTO> collection = findResult.stream().map(book -> modelMapper.map(book, PessoaV1DTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(collection);
        } catch (InternalError | Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping(path = { "/v1/pessoa/{id}" }, produces = "application/json")
    @Operation(summary = "Procurar pessoa por ID")
    public ResponseEntity<?> findByIdV1(@PathVariable("id") long id) {
        try {
            Pessoa pessoa = service.findById(id);
            PessoaV1DTO pessoaResponse = modelMapper.map(pessoa, PessoaV1DTO.class);
            return ResponseEntity.ok().body(pessoaResponse);
        } catch (BadRequest br) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getMessage());
        } catch (InternalError | Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping(path = "v1/{id}", produces = "application/json")
    @Operation(summary = "Deletar pessoa")
    public ResponseEntity<?> deleteByIDV1(@PathVariable("id") long id) {
        try {
            service.delete(id);
            return this.findAllV1(0, 10, "id");
        } catch (BadRequest br) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getMessage());
        } catch (InternalError | Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

}