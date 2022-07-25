package br.com.alura.comex.comercial.dominio.pedido;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.alura.comex.comercial.dominio.pedido.descontos.itempedido.CalculadoraDeDescontosItemPedido;
import br.com.alura.comex.comercial.dominio.pedido.descontos.itempedido.TipoDescontoItemPedido;
import br.com.alura.comex.comercial.dominio.produto.Produto;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "preco_unitario")
    private BigDecimal precoUnitario = BigDecimal.ZERO;
    @Column(nullable = false)
    private int quantidade = 0;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Pedido pedido;

    @Column(nullable = false)
    private BigDecimal desconto;

    @Enumerated(EnumType.STRING)
    private TipoDescontoItemPedido tipoDesconto;

    public Long getId() {
        return id;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Produto produto, int quantidade) {
        this.quantidade = quantidade;
        produto.retirarDoEstoqueParaOPedido(quantidade);
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto() {
        this.desconto = new CalculadoraDeDescontosItemPedido().calcular(this).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getValorTotal() {
        return this.precoUnitario.multiply(new BigDecimal(this.quantidade));
    }

    public BigDecimal getValorTotalComDesconto() {
        return this.getValorTotal().subtract(this.desconto);
    }

    public TipoDescontoItemPedido getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(TipoDescontoItemPedido tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    @Override
    public String toString() {
        return "ItemPedido [id=" + id +
                ", precoUnitario=" + precoUnitario +
                ", quantidade=" + quantidade +
                ", produto=" + produto +
                ", pedido=" + pedido +
                ", desconto=" + desconto +
                ", valorTotal=" + getValorTotal() + "]";
    }

}
