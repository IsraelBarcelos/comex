package br.com.alura.comex.models.descontos.descontoItemPedido;

import java.math.BigDecimal;

import br.com.alura.comex.models.ItemPedido;

public class CalculadoraDeDescontosItemPedido {

    public BigDecimal calcular(ItemPedido itemPedido) {
        DescontoItemPedido desconto = new DescontoPorQuantidade(
                new DescontoPorPromocao(new SemDesconto(null), new BigDecimal(20.00)));

        return desconto.calcula(itemPedido);
    }
}
