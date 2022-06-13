package br.com.alura.comex.builders;

import java.math.BigDecimal;

import br.com.alura.comex.models.ItemPedido;
import br.com.alura.comex.models.Pedido;
import br.com.alura.comex.models.Produto;

public class ItemPedidoBuilder {

    private ItemPedido itemPedido;

    public ItemPedidoBuilder() {
        itemPedido = new ItemPedido();
    }

    public ItemPedidoBuilder comPrecoUnitario(BigDecimal precoUnitario) {
        itemPedido.setPrecoUnitario(precoUnitario);
        return this;
    }

    public ItemPedidoBuilder comQuantidade(int quantidade) {
        itemPedido.setQuantidade(quantidade);
        return this;
    }

    public ItemPedidoBuilder comProduto(Produto produto) {
        itemPedido.setProduto(produto);
        return this;
    }

    public ItemPedidoBuilder comPedido(Pedido pedido) {
        itemPedido.setPedido(pedido);
        return this;
    }

    public ItemPedidoBuilder comDesconto(BigDecimal desconto) {
        itemPedido.setDesconto(desconto);
        return this;
    }

    public ItemPedido build() {
        return itemPedido;
    }

}
