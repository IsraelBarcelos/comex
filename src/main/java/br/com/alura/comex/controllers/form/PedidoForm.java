package br.com.alura.comex.controllers.form;

import br.com.alura.comex.builders.PedidoBuilder;
import br.com.alura.comex.models.ItemPedido;
import br.com.alura.comex.models.Pedido;
import br.com.alura.comex.repository.ClienteRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PedidoForm {

  @NotNull
  @NotEmpty
  private LocalDate data;
  @NotNull
  @NotEmpty
  private BigDecimal desconto;
  @NotNull
  @NotEmpty
  private String nomeCliente;
  private List<ItemPedido> itens;

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

  public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
  }

  public List<ItemPedido> getItens() {
    return itens;
  }

  public void setItens(List<ItemPedido> itens) {
    this.itens = itens;
  }

  public Pedido converter(ClienteRepository clienteRepository) {
    Pedido pedido = new PedidoBuilder()
        .comCliente(clienteRepository.findByNome(nomeCliente))
        .comData(data)
        .comDesconto(desconto)
        .build();

    return pedido;
  }

  public Pedido converterComItens(
      ClienteRepository clienteRepository,
      List<ItemPedido> itens) {
    Pedido pedido = new PedidoBuilder()
        .comCliente(clienteRepository.findByNome(nomeCliente))
        .comData(data)
        .comDesconto(desconto)
        .comItens(itens)
        .build();

    return pedido;
  }
}
