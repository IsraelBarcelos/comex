package br.com.alura.comex.comercial.aplicacao.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.comex.comercial.dominio.pedido.Pedido;

public class DetalhamentoDePedidoDto {
    private LocalDateTime data;
    private BigDecimal valorTotal;
    private BigDecimal desconto;
    private List<ItemPedidoDetalhamentoPedidoDto> itens;
    private Long idCliente;
    private String nomeCliente;

    public DetalhamentoDePedidoDto(Pedido pedido) {
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorTotal();
        this.desconto = pedido.getDesconto();
        this.itens = ItemPedidoDetalhamentoPedidoDto.converter(pedido.getItensPedido());
        this.idCliente = pedido.getCliente().getId();
        this.nomeCliente = pedido.getCliente().getNome();
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public String getCpfCliente() {
        return nomeCliente;
    }

    public void setCpfCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemPedidoDetalhamentoPedidoDto> getItens() {
        return itens;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public static List<PedidoDto> converter(List<Pedido> pedidos) {
        return pedidos.stream().map(PedidoDto::new).collect(Collectors.toList());
    }
}
