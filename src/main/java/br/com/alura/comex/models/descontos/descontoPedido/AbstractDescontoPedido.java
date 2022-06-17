package br.com.alura.comex.models.descontos.descontoPedido;

import java.math.BigDecimal;

import br.com.alura.comex.models.Pedido;

public abstract class AbstractDescontoPedido {

    AbstractDescontoPedido successor;

    public AbstractDescontoPedido(AbstractDescontoPedido successor) {
        this.successor = successor;
    }

    public abstract BigDecimal calcula(Pedido pedido);
}
