package br.com.alura.comex.comercial.dominio.pedido.descontos.itempedido;

import java.math.BigDecimal;

import br.com.alura.comex.comercial.dominio.pedido.ItemPedido;

public abstract class AbstractDescontoItemPedido {

    AbstractDescontoItemPedido successor;

    protected AbstractDescontoItemPedido(AbstractDescontoItemPedido successor) {
        this.successor = successor;
    }

    public BigDecimal deveCalcular(ItemPedido itemPedido) {
        if (deveAplicar(itemPedido)) {
            return calcula(itemPedido);
        }

        return successor.calcula(itemPedido);
    }

    public abstract BigDecimal calcula(ItemPedido itemPedido);

    public abstract boolean deveAplicar(ItemPedido itemPedido);

}