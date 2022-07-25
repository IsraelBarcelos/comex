package br.com.alura.comex.comercial.dominio.pedido.descontos.pedido;

import java.math.BigDecimal;

import br.com.alura.comex.comercial.dominio.pedido.Pedido;
import br.com.alura.comex.comercial.infra.pedido.PedidoRepositoryComJPA;

public class CalculadoraDeDescontosPedido {
    public BigDecimal calcular(Pedido pedido, PedidoRepositoryComJPA pedidoRepository) {
        AbstractDescontoPedido desconto = new DescontoFidelidade(new SemDesconto(null), pedidoRepository);
        return desconto.calcula(pedido);
    }
}