package br.com.alura.comex.infra.pedido;

import br.com.alura.comex.dominio.cliente.ValidaIdCliente;
import br.com.alura.comex.dominio.pedido.Pedido;
import br.com.alura.comex.dominio.pedido.PedidoBuilder;
import br.com.alura.comex.infra.cliente.ClienteRepositoryComJPA;
import br.com.alura.comex.infra.produto.ProdutoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

public class PedidoForm {

    @NotNull
    @ValidaIdCliente
    private Long clienteId;
    @NotNull
    private List<AdicionarItemPedidoDto> itens;

    public Long getClienteId() {
        return this.clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<AdicionarItemPedidoDto> getItens() {
        return this.itens;
    }

    public void setItens(List<AdicionarItemPedidoDto> itens) {
        this.itens = itens;
    }

    public Pedido converter(ClienteRepositoryComJPA clienteRepository, ProdutoRepository produtoRepository,
            PedidoRepository pedidoRepository) {
        Pedido pedido = new PedidoBuilder()
                .comCliente(clienteRepository.findById(clienteId).get())
                .comItens(itens.stream().map(item -> item.converter(produtoRepository)).collect(Collectors.toList()))
                .comData(LocalDateTime.now())
                .comDesconto(pedidoRepository)
                .build();

        return pedido;
    }

}
