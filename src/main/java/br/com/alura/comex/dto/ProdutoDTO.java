package br.com.alura.comex.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.comex.models.Produto;

public class ProdutoDTO {

    private String nome;
    private String descricao;
    private BigDecimal precoUnitariopreco;
    private Integer quantidadeEstoque;

    public ProdutoDTO(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.precoUnitariopreco = produto.getPrecoUnitario();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPrecoUnitariopreco() {
        return precoUnitariopreco;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public static List<ProdutoDTO> converter(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }
}
