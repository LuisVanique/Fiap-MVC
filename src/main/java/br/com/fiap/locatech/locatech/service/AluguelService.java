package br.com.fiap.locatech.locatech.service;

import br.com.fiap.locatech.locatech.dtos.AluguelRequestDto;
import br.com.fiap.locatech.locatech.entities.Aluguel;
import br.com.fiap.locatech.locatech.repository.AluguelRepository;
import br.com.fiap.locatech.locatech.repository.VeiculoRepository;
import br.com.fiap.locatech.locatech.service.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;
    private final VeiculoRepository veiculoRepository;


    public AluguelService(AluguelRepository aluguelRepository, VeiculoRepository veiculoRepository) {
        this.aluguelRepository = aluguelRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public List<Aluguel> findAllAlugueis(int page, int size) {
        int offset = (page - 1) * size;
        return this.aluguelRepository.findAll(size, offset);
    }

    public Optional<Aluguel> findAluguelById(Long id) {
        return Optional.ofNullable(this.aluguelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aluguel não encontrado")));
    }

    public void saveAluguel(AluguelRequestDto aluguel) {
        var aluguelEntity = calculaAluguel(aluguel);
        var save = aluguelRepository.save(aluguelEntity);
        Assert.state(save == 1, "Erro ao salvar aluguel " + aluguel.pessoaId());
    }

    public void updateAluguel(AluguelRequestDto aluguelRequestDto, Long id) {
        var aluguelEntity = calculaAluguel(aluguelRequestDto);
        var update = aluguelRepository.update(aluguelEntity, id);
        if (update == 0) {
            throw new RuntimeException("Aluguel nao encontrado");
        }
    }

    public void delete(Long id) {
        var delete = aluguelRepository.delete(id);
        if (delete == 0) {
            throw new RuntimeException("Aluguel nao encontrado");
        }
    }

    private Aluguel calculaAluguel(AluguelRequestDto aluguelRequestDto){
        var veiculo = this.veiculoRepository.findById(aluguelRequestDto.veiculoId())
                .orElseThrow(() -> new RuntimeException("Veiculo não encontrado"));

        var quantidadeDeDias = BigDecimal.valueOf(aluguelRequestDto.dataFim().getDayOfYear() - aluguelRequestDto.dataInicio().getDayOfYear());
        var valor = veiculo.getValorDiaria().multiply(quantidadeDeDias);
        return new Aluguel(aluguelRequestDto, valor);
    }
}
