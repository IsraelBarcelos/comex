package br.com.alura.mains;

import br.com.alura.dao.ClienteDAO;
import br.com.alura.models.*;
import br.com.alura.utils.JPAUtil;
import javax.persistence.EntityManager;

/**
 * Hello world!
 *
 */
public class App {

  public static void main(String[] args) {
    Categoria categoria = new Categoria();
    categoria.setNome("Informática");
    categoria.setAtivo(true);

    EntityManager em = JPAUtil.getEntityManager();

    em.getTransaction().begin();

    ClienteDAO clienteDao = new ClienteDAO(em);
    em.getTransaction().commit();

    clienteDao.listaTodos().forEach(System.out::println);
  }
}
