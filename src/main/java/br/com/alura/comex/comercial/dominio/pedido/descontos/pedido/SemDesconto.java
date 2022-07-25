package br.com.alura.comex.comercial.dominio.pedido.descontos.pedido;

import java.math.BigDecimal;

import br.com.alura.comex.comercial.dominio.pedido.Pedido;

public class SemDesconto extends AbstractDescontoPedido {

    public SemDesconto(AbstractDescontoPedido successor) {
        super(null);
    }

    @Override
    public BigDecimal calcula(Pedido pedido) {
        pedido.setTipoDesconto(TipoDescontoPedido.NENHUM);
        return BigDecimal.ZERO;
    }

    @Override
    public boolean deveAplicar(Pedido pedido) {
        return true;
    }

}
