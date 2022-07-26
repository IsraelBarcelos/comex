package br.com.alura.comex.comercial.dominio.produto;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.alura.comex.comercial.dominio.categoria.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(nullable = false, name = "preco_unitario")
    private BigDecimal precoUnitario = BigDecimal.ZERO;

    @Column(nullable = false, name = "quantidade_estoque")
    private int quantidadeEstoque = 0;

    @OneToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    public void retirarDoEstoqueParaOPedido(int quantidadeASerRetirada) {
        if (quantidadeASerRetirada > quantidadeEstoque) {
            throw new IllegalArgumentException("Não há quantidade suficiente de " + this.nome + " no estoque");
        }
        this.quantidadeEstoque -= quantidadeASerRetirada;
    }

    public void adicionarProdutoAoEstoque(int quantidadeASerAdicionada) {
        this.quantidadeEstoque += quantidadeASerAdicionada;
    }

}
