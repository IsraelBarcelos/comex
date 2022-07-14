package br.com.alura.comex.aplicacao.pedido;

import javax.validation.constraints.NotNull;

import br.com.alura.comex.dominio.pedido.ItemPedido;
import br.com.alura.comex.dominio.pedido.ItemPedidoBuilder;
import br.com.alura.comex.dominio.produto.Produto;
import br.com.alura.comex.infra.produto.ProdutoRepository;

public class AdicionarItemPedidoDto {

    @NotNull
    private Long produtoId;
    @NotNull
    private Integer quantidade;

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public ItemPedido converter(ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.findById(produtoId).get();
        return new ItemPedidoBuilder()
                .comProduto(produto)
                .comPrecoUnitario(produto.getPrecoUnitario())
                .comQuantidade(quantidade)
                .comDesconto()
                .build();
    }
}
