package br.com.alura.comex.comercial.infra.pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.comex.comercial.dominio.pedido.Pedido;
import br.com.alura.comex.comercial.dominio.pedido.RepositorioDePedido;

public interface PedidoRepositoryComJPA extends RepositorioDePedido, JpaRepository<Pedido, Long> {

    @Query("select p from Pedido p join p.itens itens where itens.produto.categoria.nome = ?1")
    List<Pedido> findByItemPedidoProdutoCategoriaNome(String nomeCategoria);

    @Query("select count(p) from Pedido p where p.cliente.id = ?1")
    Integer numeroDePedidosDeUmCliente(Long clienteId);

    @Query("select p from Pedido p")
    List<Pedido> listarTodosPedidos();

    @Query("select p from Pedido p where p.id = ?1")
    Optional<Pedido> encontrarPedidoPeloId(Long id);

}
