package br.com.alura.dao;

import br.com.alura.models.Categoria;
import br.com.alura.models.ItemPedido;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

public class CategoriaDAO {

  private EntityManager em;

  public CategoriaDAO(EntityManager em) {
    this.em = em;
  }

  public void cadastra(Categoria categoria) {
    em.persist(categoria);
  }

  public Categoria buscaPorId(Long id) {
    return em.find(Categoria.class, id);
  }

  public void remove(Categoria categoria) {
    em.remove(categoria);
  }

  public void atualiza(Categoria categoria) {
    em.merge(categoria);
  }

  public List<Categoria> listaTodos() {
    return this.em.createQuery("select c from Categoria c", Categoria.class)
        .getResultList();
  }

  public void relatorioMontantePorCategoria() {
    List<Categoria> categorias = this.listaTodos();
    categorias.stream().forEach(categoria -> {
      BigDecimal soma = new ProdutoDAO(em).produtosDeUmaCategoria(categoria).stream().reduce(BigDecimal.ZERO,
          (total, produto) -> total.add(produto.getPrecoUnitario()),
          (total, total2) -> total.add(total2));
      System.out.println("Categoria " + categoria.getNome() + " tem soma = " + soma);

      ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO(em);
      List<ItemPedido> itens = itemPedidoDAO.listaPorCategoria(categoria);
      System.out.println(" e vendeu " + itens.size() + " itens");

    }

    );
  }

}
