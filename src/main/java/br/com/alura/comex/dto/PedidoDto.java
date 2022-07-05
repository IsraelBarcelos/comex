package br.com.alura.comex.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.alura.comex.models.Pedido;

public class PedidoDto {

    private LocalDateTime data;
    private BigDecimal valorTotal;
    private BigDecimal desconto;
    private Integer quantidadeDeItens;
    private String nomeCliente;

    public PedidoDto(Pedido pedido) {
        this.data = pedido.getData();
        this.desconto = pedido.getDesconto();
        this.nomeCliente = pedido.getCliente().getNome();
        this.valorTotal = pedido.getValorTotal();
        this.quantidadeDeItens = pedido.getItensPedido().stream().map(itens -> {
            return itens.getQuantidade();
        }).reduce(0, Integer::sum);
    };

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

    public String getNomeCliente() {
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

    public Integer getQuantidadeDeItens() {
        return quantidadeDeItens;
    }

    public static Page<PedidoDto> converter(Page<Pedido> pedidos) {
        return pedidos.map(PedidoDto::new);
    }

}
