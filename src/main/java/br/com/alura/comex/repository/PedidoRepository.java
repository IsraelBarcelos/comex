package br.com.alura.comex.repository;

import br.com.alura.comex.models.Pedido;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

    @Query("select p from Pedido p join p.itens itens where itens.produto.categoria.nome = ?1")
    List<Pedido> findByItemPedidoProdutoCategoriaNome(String nomeCategoria);

    Page<Pedido> findAll(Pageable pageable);
}
