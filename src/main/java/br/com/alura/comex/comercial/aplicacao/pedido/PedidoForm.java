package br.com.alura.comex.comercial.aplicacao.pedido;

import br.com.alura.comex.comercial.dominio.cliente.Cliente;
import br.com.alura.comex.comercial.dominio.cliente.ValidaIdCliente;
import br.com.alura.comex.comercial.dominio.pedido.Pedido;
import br.com.alura.comex.comercial.dominio.pedido.PedidoBuilder;
import br.com.alura.comex.comercial.infra.cliente.ClienteRepositoryComJPA;
import br.com.alura.comex.comercial.infra.pedido.PedidoRepositoryComJPA;
import br.com.alura.comex.comercial.infra.produto.ProdutoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
            PedidoRepositoryComJPA pedidoRepository) {

        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if (!cliente.isPresent()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        return new PedidoBuilder()
                .comCliente(cliente.get())
                .comItens(itens.stream().map(item -> item.converter(produtoRepository)).collect(Collectors.toList()))
                .comData(LocalDateTime.now())
                .comDesconto(pedidoRepository)
                .build();
    }

}
