package br.com.alura.comex.dominio.pedido.descontos.descontoItemPedido;

import java.math.BigDecimal;

import br.com.alura.comex.dominio.pedido.ItemPedido;

public class CalculadoraDeDescontosItemPedido {

    public BigDecimal calcular(ItemPedido itemPedido) {
        AbstractDescontoItemPedido desconto = new DescontoPorQuantidade(
                new DescontoPorPromocao(new SemDesconto(null), new BigDecimal(20.00)));

        return desconto.calcula(itemPedido);
    }
}
