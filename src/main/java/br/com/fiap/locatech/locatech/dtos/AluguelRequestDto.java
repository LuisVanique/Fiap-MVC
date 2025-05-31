package br.com.fiap.locatech.locatech.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AluguelRequestDto(
        @Schema(description = "Id da pessoa que está alugando o Veiculo")
        @NotNull(message = "o id da pessoa não pode ser nulo")
        Long pessoaId,

        @NotNull(message = "o id do veiculo não pode ser nulo")
        Long veiculoId,

        LocalDate dataInicio,

        LocalDate dataFim
) {
}
