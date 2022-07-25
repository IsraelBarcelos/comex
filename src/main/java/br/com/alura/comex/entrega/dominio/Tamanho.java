package br.com.alura.comex.entrega.dominio;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class Tamanho {
    private BigDecimal comprimento;
    private BigDecimal largura;
    private BigDecimal altura;
}
