package br.com.alura.comex.dominio.produto;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.alura.comex.dominio.categoria.Categoria;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void retirarDoEstoqueParaOPedido(int quantidadeASerRetirada) {
        if (quantidadeASerRetirada > quantidadeEstoque) {
            throw new IllegalArgumentException("Não há quantidade suficiente de " + this.nome + " no estoque");
        }
        this.quantidadeEstoque -= quantidadeASerRetirada;
    }

    public void adicionarProdutoAoEstoque(int quantidadeASerAdicionada) {
        this.quantidadeEstoque += quantidadeASerAdicionada;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", precoUnitario=" + precoUnitario +
                ", quantidadeEstoque=" + quantidadeEstoque +
                ", categoria=" + categoria +
                '}';
    }

}
