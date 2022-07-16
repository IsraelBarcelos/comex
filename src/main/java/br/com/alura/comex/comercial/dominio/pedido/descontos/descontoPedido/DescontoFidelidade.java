package br.com.alura.comex.comercial.dominio.pedido.descontos.descontoPedido;

import java.math.BigDecimal;

import br.com.alura.comex.comercial.dominio.pedido.Pedido;
import br.com.alura.comex.comercial.infra.pedido.PedidoRepository;

public class DescontoFidelidade extends AbstractDescontoPedido {

    PedidoRepository pedidoRepository;

    public DescontoFidelidade(AbstractDescontoPedido successor, PedidoRepository pedidoRepository) {
        super(successor);
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public BigDecimal calcula(Pedido pedido) {
        pedido.setTipoDesconto(TipoDescontoPedido.FIDELIDADE);
        return pedido.getValorTotal().multiply(new BigDecimal(0.05));
    }

    @Override
    public boolean deveAplicar(Pedido pedido) {
        return pedidoRepository.pedidosDeUmCliente(pedido.getCliente().getId()) > 5;
    }

}
