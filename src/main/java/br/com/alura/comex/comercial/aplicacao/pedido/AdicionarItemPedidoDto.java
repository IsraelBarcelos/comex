package br.com.alura.comex.comercial.aplicacao.pedido;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import br.com.alura.comex.comercial.dominio.pedido.ItemPedido;
import br.com.alura.comex.comercial.dominio.produto.Produto;
import br.com.alura.comex.comercial.dominio.produto.RepositorioDeProduto;

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

    public ItemPedido converter(RepositorioDeProduto produtoRepository) {

        Optional<Produto> produto = produtoRepository.encontrarProdutoPeloId(produtoId);

        if (!produto.isPresent()) {
            throw new IllegalArgumentException("Produto não encontrado");
        }

        Produto produtoRecebido = produto.get();

        return new ItemPedidoBuilder()
                .comProduto(produtoRecebido)
                .comPrecoUnitario(produtoRecebido.getPrecoUnitario())
                .comQuantidade(quantidade)
                .comDesconto()
                .build();
    }
}
