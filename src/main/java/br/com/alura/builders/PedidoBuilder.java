package br.com.alura.builders;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.models.Cliente;
import br.com.alura.models.Pedido;
import br.com.alura.models.TipoDescontoPedido;

public class PedidoBuilder {

    private Pedido pedido;

    public PedidoBuilder() {
        this.pedido = new Pedido();
    }

    public PedidoBuilder comData(LocalDate data) {
        this.pedido.setData(data);
        return this;
    }

    public PedidoBuilder comCliente(Cliente cliente) {
        this.pedido.setCliente(cliente);
        return this;
    }

    public PedidoBuilder comDesconto(BigDecimal desconto) {
        this.pedido.setDesconto(desconto);
        return this;
    }

    public PedidoBuilder comTipoDescontoPedido(TipoDescontoPedido tipoDescontoPedido) {
        this.pedido.setTipoDesconto(tipoDescontoPedido);
        return this;
    }

    public Pedido build() {
        return this.pedido;
    }

}
