package br.com.alura.builders;

import java.math.BigDecimal;

import br.com.alura.models.Categoria;
import br.com.alura.models.Produto;

public class ProdutoBuilder {

    private Produto produto;

    public ProdutoBuilder() {
        this.produto = new Produto();
    }

    public ProdutoBuilder comNome(String nome) {
        this.produto.setNome(nome);
        return this;
    }

    public ProdutoBuilder comDescricao(String descricao) {
        this.produto.setDescricao(descricao);
        return this;
    }

    public ProdutoBuilder comPrecoUnitario(BigDecimal precoUnitario) {
        this.produto.setPrecoUnitario(precoUnitario);
        return this;
    }

    public ProdutoBuilder comQuantidadeEstoque(int quantidadeEstoque) {
        this.produto.setQuantidadeEstoque(quantidadeEstoque);
        return this;
    }

    public ProdutoBuilder comCategoria(Categoria categoria) {
        this.produto.setCategoria(categoria);
        return this;
    }

    public Produto build() {
        return this.produto;
    }

}
