package br.com.alura.comex.dto;

import br.com.alura.comex.models.Produto;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDto {

    private String nome;
    private String descricao;
    private BigDecimal precoUnitariopreco;
    private Integer quantidadeEstoque;

    public ProdutoDto(Produto produto) {
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

    public static List<ProdutoDto> converter(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
    }
}
