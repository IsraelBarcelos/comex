package br.com.alura.comex.comercial.dominio.pedido.descontos.itempedido;

import java.math.BigDecimal;

import br.com.alura.comex.comercial.dominio.pedido.ItemPedido;

public class DescontoPorPromocao extends AbstractDescontoItemPedido {

    private BigDecimal valorDesconto;

    public DescontoPorPromocao(AbstractDescontoItemPedido successor, BigDecimal valorDesconto) {
        super(successor);
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal calcula(ItemPedido itemPedido) {
        itemPedido.setTipoDesconto(TipoDescontoItemPedido.PROMOCAO);
        return valorDesconto;
    }

    @Override
    public boolean deveAplicar(ItemPedido itemPedido) {
        return valorDesconto.compareTo(new BigDecimal(0)) > 0;
    }
}
