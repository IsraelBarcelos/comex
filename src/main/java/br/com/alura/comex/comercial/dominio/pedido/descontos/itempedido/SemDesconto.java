package br.com.alura.comex.comercial.dominio.pedido.descontos.itempedido;

import java.math.BigDecimal;

import br.com.alura.comex.comercial.dominio.pedido.ItemPedido;

public class SemDesconto extends AbstractDescontoItemPedido {

    public SemDesconto(AbstractDescontoItemPedido successor) {
        super(null);
    }

    @Override
    public BigDecimal calcula(ItemPedido itemPedido) {
        itemPedido.setTipoDesconto(TipoDescontoItemPedido.NENHUM);
        return BigDecimal.ZERO;
    }

    @Override
    public boolean deveAplicar(ItemPedido itemPedido) {
        return true;
    }

}
