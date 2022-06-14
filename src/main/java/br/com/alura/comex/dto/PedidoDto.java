package br.com.alura.comex.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.comex.models.Pedido;

public class PedidoDto {

    private LocalDate data;
    private BigDecimal desconto;
    private String nomeCliente;
    private BigDecimal valorTotal;

    public PedidoDto(Pedido pedido) {
        this.data = pedido.getData();
        this.desconto = pedido.getDesconto();
        this.nomeCliente = pedido.getCliente().getNome();
        this.valorTotal = pedido.getValorTotal();
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
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

    public static List<PedidoDto> converter(List<Pedido> pedidos) {
        return pedidos.stream().map(PedidoDto::new).collect(Collectors.toList());
    }

}
