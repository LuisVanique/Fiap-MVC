package br.com.fiap.locatech.locatech.entities;

import lombok.*;

import java.math.BigDecimal;


@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class Veiculo {

    private Long id;
    private String marca;
    private String modelo;
    private String placa;
    private int ano;
    private String cor;
    private BigDecimal valorDiaria;


    public int getAno() {
        return ano;
    }

    public String getCor() {
        return cor;
    }


    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }
}
