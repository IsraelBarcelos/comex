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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "itens_pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public void setQuantidade(Produto produto, int quantidade) {
        this.quantidade = quantidade;
        produto.retirarDoEstoqueParaOPedido(quantidade);
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

}
