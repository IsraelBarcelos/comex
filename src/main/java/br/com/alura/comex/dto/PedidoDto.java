package br.com.alura.comex.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.comex.models.Pedido;

public class PedidoDto {

    private LocalDate data;
    private BigDecimal valorTotal;
    private BigDecimal desconto;
    private Integer quantidadeDeItens;
    private String nomeCliente;

    private static Comparator<Pedido> comparatorDataENomeCliente = new Comparator<Pedido>() {

        public int compare(Pedido o1, Pedido o2) {

            LocalDate x1 = o1.getData();
            LocalDate x2 = o2.getData();
            int sComp = x1.compareTo(x2);

            if (sComp != 0) {
                return -1 * sComp;
            }

            String nome1 = o1.getCliente().getNome();
            String nome2 = o2.getCliente().getNome();
            return nome1.compareTo(nome2);
        }
    };

    public PedidoDto(Pedido pedido) {
        this.data = pedido.getData();
        this.desconto = pedido.getDesconto();
        this.nomeCliente = pedido.getCliente().getNome();
        this.valorTotal = pedido.getValorTotal();
        this.quantidadeDeItens = pedido.getItensPedido().stream().map(itens -> {
            return itens.getQuantidade();
        }).reduce(0, Integer::sum);
    };

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setCpfCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getQuantidadeDeItens() {
        return quantidadeDeItens;
    }

    public static List<PedidoDto> converter(List<Pedido> pedidos) {
        return pedidos.stream()
                .sorted(comparatorDataENomeCliente)
                .map(PedidoDto::new)
                .collect(Collectors.toList());
    }

}
