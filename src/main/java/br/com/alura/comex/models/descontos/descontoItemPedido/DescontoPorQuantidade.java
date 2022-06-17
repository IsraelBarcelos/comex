package br.com.alura.comex.models.descontos.descontoItemPedido;

import java.math.BigDecimal;

import br.com.alura.comex.models.ItemPedido;

public class DescontoPorQuantidade extends DescontoItemPedido {

    public DescontoPorQuantidade(DescontoItemPedido successor) {
        super(successor);
    }

    public BigDecimal calcula(ItemPedido itemPedido) {
        if (itemPedido.getQuantidade() > 10) {
            itemPedido.setTipoDesconto(TipoDescontoItemPedido.QUANTIDADE);
            return itemPedido.getValorTotal().multiply(new BigDecimal(0.1));
        }
        return successor.calcula(itemPedido);
    }
}