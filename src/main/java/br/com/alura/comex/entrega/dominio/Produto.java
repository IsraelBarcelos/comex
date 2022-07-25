package br.com.alura.comex.entrega.dominio;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Produto {

    private BigDecimal preco;
    private BigDecimal precoDeFrete;
    private Tamanho tamanho;
    private BigDecimal peso;
}