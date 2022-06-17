package br.com.alura.comex.models.descontos.descontoItemPedido;

import java.math.BigDecimal;

import br.com.alura.comex.models.ItemPedido;

public class DescontoPorPromocao extends DescontoItemPedido {

    private BigDecimal valorDesconto;

    public DescontoPorPromocao(DescontoItemPedido successor, BigDecimal valorDesconto) {
        super(successor);
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal calcula(ItemPedido itemPedido) {

        System.out.println(itemPedido);

        if (valorDesconto.compareTo(new BigDecimal(0)) > 0) {
            itemPedido.setTipoDesconto(TipoDescontoItemPedido.PROMOCAO);

            return valorDesconto;
        }
        return successor.calcula(itemPedido);
    }
}
