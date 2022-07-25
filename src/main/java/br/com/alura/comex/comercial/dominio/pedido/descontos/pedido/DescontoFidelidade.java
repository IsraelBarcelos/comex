package br.com.alura.comex.comercial.dominio.pedido.descontos.pedido;

import java.math.BigDecimal;

import br.com.alura.comex.comercial.dominio.pedido.Pedido;
import br.com.alura.comex.comercial.infra.pedido.PedidoRepositoryComJPA;

public class DescontoFidelidade extends AbstractDescontoPedido {

    PedidoRepositoryComJPA pedidoRepository;

    public DescontoFidelidade(AbstractDescontoPedido successor, PedidoRepositoryComJPA pedidoRepository) {
        super(successor);
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public BigDecimal calcula(Pedido pedido) {
        pedido.setTipoDesconto(TipoDescontoPedido.FIDELIDADE);
        return pedido.getValorTotal().multiply(BigDecimal.valueOf(0.05));
    }

    @Override
    public boolean deveAplicar(Pedido pedido) {
        return pedidoRepository.numeroDePedidosDeUmCliente(pedido.getCliente().getId()) > 5;
    }

}
