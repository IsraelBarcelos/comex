package br.com.alura.comex.controllers.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.comex.models.Produto;
import br.com.alura.comex.repository.ProdutoRepository;

public class AtualizacaoProdutoForm {
    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String nome;
    private String descricao;
    @NotNull
    private BigDecimal precoUnitario;
    @NotNull
    private Integer quantidadeEstoque;
    @NotNull
    @NotEmpty
    private String nomeCategoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public Produto atualizar(Long id, ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.getReferenceById(id);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPrecoUnitario(precoUnitario);
        produto.getCategoria().setNome(nomeCategoria);
        produto.setQuantidadeEstoque(quantidadeEstoque);

        return produto;
    }
}
