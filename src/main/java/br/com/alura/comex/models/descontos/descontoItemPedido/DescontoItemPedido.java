package br.com.alura.comex.models.descontos.descontoItemPedido;

import java.math.BigDecimal;

import br.com.alura.comex.models.ItemPedido;

public abstract class DescontoItemPedido {

    DescontoItemPedido successor;

    public DescontoItemPedido(DescontoItemPedido successor) {
        this.successor = successor;
    }

    public abstract BigDecimal calcula(ItemPedido itemPedido);

}
