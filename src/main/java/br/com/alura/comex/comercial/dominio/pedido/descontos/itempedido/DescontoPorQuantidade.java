package br.com.alura.comex.comercial.dominio.pedido.descontos.itempedido;

import java.math.BigDecimal;

import br.com.alura.comex.comercial.dominio.pedido.ItemPedido;

public class DescontoPorQuantidade extends AbstractDescontoItemPedido {

    public DescontoPorQuantidade(AbstractDescontoItemPedido successor) {
        super(successor);
    }

    public BigDecimal calcula(ItemPedido itemPedido) {
        itemPedido.setTipoDesconto(TipoDescontoItemPedido.QUANTIDADE);
        return itemPedido.getValorTotal().multiply(BigDecimal.valueOf(0.1));
    }

    @Override
    public boolean deveAplicar(ItemPedido itemPedido) {
        return itemPedido.getQuantidade() > 10;
    }
}