package br.com.alura.comex.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.alura.comex.models.descontos.descontoPedido.CalculadoraDeDescontosPedido;
import br.com.alura.comex.models.descontos.descontoPedido.TipoDescontoPedido;
import br.com.alura.comex.repository.PedidoRepository;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private BigDecimal desconto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo_desconto")
    private TipoDescontoPedido tipoDesconto;

    @Column(nullable = false)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(PedidoRepository pedidoRepository) {
        this.desconto = new CalculadoraDeDescontosPedido().calcular(this, pedidoRepository).setScale(2,
                RoundingMode.HALF_UP);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoDescontoPedido getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(TipoDescontoPedido tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public List<ItemPedido> getItensPedido() {
        return this.itens;
    }

    public void adicionarItemPedido(ItemPedido itemPedido) {
        this.valorTotal = this.valorTotal.add(itemPedido.getValorTotalComDesconto());
        this.itens.forEach(item -> {
            item.getProduto()
                    .retirarDoEstoqueParaOPedido(item.getQuantidade());
        });
        itemPedido.setPedido(this);
        this.itens.add(itemPedido);

    }
}
