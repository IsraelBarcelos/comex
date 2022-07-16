package br.com.alura.comex.comercial.dominio.pedido.descontos.descontoPedido;

import java.math.BigDecimal;

import br.com.alura.comex.comercial.dominio.pedido.Pedido;
import br.com.alura.comex.comercial.infra.pedido.PedidoRepository;

public class CalculadoraDeDescontosPedido {
    public BigDecimal calcular(Pedido pedido, PedidoRepository pedidoRepository) {
        AbstractDescontoPedido desconto = new DescontoFidelidade(new SemDesconto(null), pedidoRepository);
        return desconto.calcula(pedido);
    }
}