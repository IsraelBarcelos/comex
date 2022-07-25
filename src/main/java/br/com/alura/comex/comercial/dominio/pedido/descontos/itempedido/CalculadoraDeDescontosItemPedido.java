package br.com.alura.comex.comercial.dominio.pedido.descontos.itempedido;

import java.math.BigDecimal;

import br.com.alura.comex.comercial.dominio.pedido.ItemPedido;

public class CalculadoraDeDescontosItemPedido {

    public BigDecimal calcular(ItemPedido itemPedido) {
        AbstractDescontoItemPedido desconto = new DescontoPorQuantidade(
                new DescontoPorPromocao(new SemDesconto(null), BigDecimal.valueOf(20.00)));

        return desconto.calcula(itemPedido);
    }
}
