package br.com.alura.comex.models.descontos.descontoPedido;

import java.math.BigDecimal;

import br.com.alura.comex.models.Pedido;
import br.com.alura.comex.repository.PedidoRepository;

public class DescontoFidelidade extends AbstractDescontoPedido {

    PedidoRepository pedidoRepository;

    public DescontoFidelidade(AbstractDescontoPedido successor, PedidoRepository pedidoRepository) {
        super(successor);
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public BigDecimal calcula(Pedido pedido) {
        if (pedidoRepository.pedidosDeUmCliente(pedido.getCliente().getId()) > 5) {
            pedido.setTipoDesconto(TipoDescontoPedido.FIDELIDADE);
            return pedido.getValorTotal().multiply(new BigDecimal(0.05));
        }
        return successor.calcula(pedido);
    }

}
