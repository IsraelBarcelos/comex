package br.com.alura.vo;

public class RelatorioDeProdutosMaisVendidosVO {
    private String nomeDoProduto;
    private Long quantidadeDoProduto;

    public RelatorioDeProdutosMaisVendidosVO() {

    }

    public RelatorioDeProdutosMaisVendidosVO(String nomeDoProduto, Long quantidadeDoProduto) {
        this.nomeDoProduto = nomeDoProduto;
        this.quantidadeDoProduto = quantidadeDoProduto;
    }

    public String getNomeDoProduto() {
        return nomeDoProduto;
    }

    public Long getQuantidadeDoProduto() {
        return quantidadeDoProduto;
    }
}
