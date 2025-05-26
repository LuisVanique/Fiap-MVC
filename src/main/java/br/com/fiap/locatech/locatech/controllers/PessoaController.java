package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.entities.Pessoa;
import br.com.fiap.locatech.locatech.entities.Pessoa;
import br.com.fiap.locatech.locatech.service.PessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/pessoas")
@RestController
public class PessoaController {

    private final PessoaService pessoaService;

    private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }
    
    //http://localhost:8080/Pessoas?page=1&size=10
    @GetMapping
    public ResponseEntity<List<Pessoa>> findAllPessoa(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        logger.info("/Pessoas");
        var pessoas = pessoaService.findAllPessoas(page, size);
        return ResponseEntity.ok(pessoas);
    }

    //http://localhost:8080/Pessoas/1
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> findPessoaById(
            @PathVariable("id") Long id) {

        logger.info("/Pessoas/" + id);
        var pessoa = this.pessoaService.findPessoaById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<Void> savePessoa(@RequestBody Pessoa pessoa) {
        logger.info("POST -> /Pessoas");
        this.pessoaService.savePessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePessoa(@RequestBody Pessoa pessoa, @PathVariable Long id){
        logger.info("PUT -> /Pessoas");
        this.pessoaService.updatePessoa(pessoa, id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable("id") Long id) {
        logger.info("DELETE -> /Pessoas");
        this.pessoaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
