package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.entities.Veiculo;
import br.com.fiap.locatech.locatech.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
@Tag(name = "Veículo", description = "Controller para crud de Veiculo")
public class VeiculoController {

    private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }


    @Operation(
            description = "Busca todos os veiculos paginados",
            summary = "Busca de Veiculos",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }

    )
    @GetMapping
    public ResponseEntity<List<Veiculo>> findAllVeiculo(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        logger.info("/veiculos");
        var veiculos = veiculoService.findAllVeiculos(page, size);
        return ResponseEntity.ok(veiculos);
    }

    //http://localhost:8080/veiculos/1
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Veiculo>> findVeiculoById(
            @PathVariable("id") Long id) {

        logger.info("/veiculos/" + id);
        var veiculo = this.veiculoService.findVeiculoById(id);
        return ResponseEntity.ok(veiculo);
    }

    @PostMapping
    public ResponseEntity<Void> saveVeiculo(@RequestBody Veiculo veiculo) {
        logger.info("POST -> /veiculos");
        this.veiculoService.saveVeiculo(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(@RequestBody Veiculo veiculo, @PathVariable Long id){
        logger.info("PUT -> /veiculos");
        this.veiculoService.updateVeiculo(veiculo, id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable("id") Long id) {
        logger.info("DELETE -> /veiculos");
        this.veiculoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
