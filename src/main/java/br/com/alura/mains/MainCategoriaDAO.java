package br.com.alura.mains;

import br.com.alura.builders.CategoriaBuilder;
import br.com.alura.dao.CategoriaDAO;
import br.com.alura.models.Categoria;
import br.com.alura.utils.JPAUtil;
import javax.persistence.EntityManager;

public class MainCategoriaDAO {

  public static void main(String[] args) {
    EntityManager em = JPAUtil.getEntityManager();

    em.getTransaction().begin();

    CategoriaDAO categoriaDAO = new CategoriaDAO(em);

    Categoria categoria1 = new CategoriaBuilder()
        .ativo()
        .comNome("carros")
        .build();

    Categoria categoria2 = new CategoriaBuilder()
        .ativo()
        .comNome("motos")
        .build();

    Categoria categoria3 = new CategoriaBuilder()
        .ativo()
        .comNome("avioes")
        .build();

    categoriaDAO.cadastra(categoria1);
    categoriaDAO.cadastra(categoria2);
    categoriaDAO.cadastra(categoria3);

    em.flush();

    categoria1.setAtivo(false);

    em.getTransaction().commit();

    Categoria categoria1editada = categoriaDAO.buscaPorId(categoria1.getId());
    System.out.println(categoria1editada);
    categoriaDAO.relatorioMontantePorCategoria();

    em.close();

  }
}
