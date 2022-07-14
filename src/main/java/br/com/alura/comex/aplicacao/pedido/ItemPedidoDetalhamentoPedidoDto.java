package br.com.alura.comex.aplicacao.pedido;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.comex.dominio.pedido.ItemPedido;

public class ItemPedidoDetalhamentoPedidoDto {
    private Long id;
    private Long idProdutoAssociado;
    private String nomeProduto;
    private String categoria;
    private Integer quantidade;
    private BigDecimal valorUnitarioItemPedido;
    private BigDecimal valorUnitarioProduto;
    private BigDecimal desconto;

    public ItemPedidoDetalhamentoPedidoDto(ItemPedido itemPedido) {
        this.id = itemPedido.getId();
        this.idProdutoAssociado = itemPedido.getProduto().getId();
        this.nomeProduto = itemPedido.getProduto().getNome();
        this.categoria = itemPedido.getProduto().getCategoria().getNome();
        this.quantidade = itemPedido.getQuantidade();
        this.valorUnitarioItemPedido = itemPedido.getProduto().getPrecoUnitario();
        this.valorUnitarioProduto = itemPedido.getProduto().getPrecoUnitario();
        this.desconto = itemPedido.getDesconto();
    }

    public Long getId() {
        return this.id;
    }

    public Long getIdProdutoAssociado() {
        return this.idProdutoAssociado;
    }

    public String getNomeProduto() {
        return this.nomeProduto;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public Integer getQuantidade() {
        return this.quantidade;
    }

    public BigDecimal getValorUnitarioItemPedido() {
        return this.valorUnitarioItemPedido;
    }

    public BigDecimal getValorUnitarioProduto() {
        return this.valorUnitarioProduto;
    }

    public BigDecimal getDesconto() {
        return this.desconto;
    }

    public static List<ItemPedidoDetalhamentoPedidoDto> converter(List<ItemPedido> itensPedido) {
        return itensPedido.stream().map(ItemPedidoDetalhamentoPedidoDto::new).collect(Collectors.toList());
    }

}
