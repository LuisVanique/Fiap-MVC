package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.dtos.AluguelRequestDto;
import br.com.fiap.locatech.locatech.entities.Aluguel;
import br.com.fiap.locatech.locatech.entities.Pessoa;
import br.com.fiap.locatech.locatech.service.AluguelService;
import br.com.fiap.locatech.locatech.service.PessoaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/alugueis")
public class AluguelController {

    private final AluguelService aluguelService;

    private static final Logger logger = LoggerFactory.getLogger(AluguelController.class);

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    //http://localhost:8080/alugueis?page=1&size=10
    @GetMapping
    public ResponseEntity<List<Aluguel>> findAllAlugueis(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        logger.info("/alugueis");
        var alugueis = aluguelService.findAllAlugueis(page, size);
        return ResponseEntity.ok(alugueis);
    }

    //http://localhost:8080/alugueis/1
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluguel>> findAluguelById(
            @PathVariable("id") Long id) {

        logger.info("/alugueis/" + id);
        var aluguel = this.aluguelService.findAluguelById(id);
        return ResponseEntity.ok(aluguel);
    }

    @PostMapping
    public ResponseEntity<Void> saveAluguel(@Valid @RequestBody AluguelRequestDto aluguel) {
        logger.info("POST -> /alugueis");
        this.aluguelService.saveAluguel(aluguel);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAluguel(@RequestBody AluguelRequestDto aluguel, @PathVariable("id") Long id){
        logger.info("PUT -> /alugueis");
        this.aluguelService.updateAluguel(aluguel, id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlugel(@PathVariable("id") Long id) {
        logger.info("DELETE -> /alugueis");
        this.aluguelService.delete(id);
        return ResponseEntity.ok().build();
    }

}
