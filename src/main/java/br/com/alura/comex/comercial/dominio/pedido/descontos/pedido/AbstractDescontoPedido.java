package br.com.alura.comex.comercial.dominio.pedido.descontos.pedido;

import java.math.BigDecimal;

import br.com.alura.comex.comercial.dominio.pedido.Pedido;

public abstract class AbstractDescontoPedido {

    AbstractDescontoPedido successor;

    protected AbstractDescontoPedido(AbstractDescontoPedido successor) {
        this.successor = successor;
    }

    public BigDecimal deveCalcular(Pedido pedido) {
        if (deveAplicar(pedido)) {
            return calcula(pedido);
        }

        return successor.calcula(pedido);
    }

    public abstract BigDecimal calcula(Pedido pedido);

    public abstract boolean deveAplicar(Pedido pedido);
}
