package br.com.fiap.locatech.locatech.entities;

import br.com.fiap.locatech.locatech.dtos.AluguelRequestDto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Aluguel {
    private Long id;
    private Long pessoaId;
    private Long veiculoId;
    private String veiculoModelo;

    private String pessoaCpf;
    private String pessoaNome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private BigDecimal valorTotal;

    public Aluguel(AluguelRequestDto dto, BigDecimal valor){
        this.pessoaId = dto.pessoaId();
        this.veiculoId = dto.veiculoId();
        this.dataInicio = dto.dataInicio();
        this.dataFim = dto.dataFim();
        this.valorTotal = valor;
    }
}
