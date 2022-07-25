package br.com.alura.comex.comercial.dominio.pedido;

import java.util.List;
import java.util.Optional;

public interface RepositorioDePedido {
    void criarPedido(Pedido pedido);

    Optional<Pedido> encontrarPedidoPeloId(Long id);

    List<Pedido> listarTodosPedidos();

    List<Pedido> listarPedidosDeUmCliente(Long clienteId);

    Integer numeroDePedidosDeUmCliente(Long clienteId);
}
