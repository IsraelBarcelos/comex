package br.com.alura.dao;

import br.com.alura.models.Cliente;
import br.com.alura.models.Pedido;

import java.util.List;

import javax.persistence.EntityManager;

public class PedidoDAO {

  private EntityManager em;

  public PedidoDAO(EntityManager em) {
    this.em = em;
  }

  public void cadastra(Pedido pedido) {
    em.persist(pedido);
  }

  public Pedido buscaPorId(Long id) {
    return em.find(Pedido.class, id);
  }

  public void remove(Pedido pedido) {
    em.remove(pedido);
  }

  public void atualiza(Pedido pedido) {
    em.merge(pedido);
  }

  public List<Pedido> listaTodos() {
    return this.em.createQuery("select p from Pedido p", Pedido.class).getResultList();
  }

  public List<Pedido> listaPorCliente(Cliente cliente) {
    return this.em.createQuery(
        "select p from Pedido p where p.cliente = :cliente",
        Pedido.class)
        .setParameter("cliente", cliente)
        .getResultList();
  }

  public int quantidadeDePedidosDeUmCliente(Cliente cliente) {
    return em.createQuery(
        "select count(p) from Pedido p where p.cliente.id = :cliente_id",
        Long.class)
        .setParameter("cliente_id", cliente.getId())
        .getSingleResult()
        .intValue();
  }
}
