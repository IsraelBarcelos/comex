package br.com.alura.comex.infra.pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.comex.dominio.pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("select p from Pedido p join p.itens itens where itens.produto.categoria.nome = ?1")
    List<Pedido> findByItemPedidoProdutoCategoriaNome(String nomeCategoria);

    @Query("select count(p) from Pedido p where p.cliente.id = ?1")
    Integer pedidosDeUmCliente(Long clienteId);
}
