package br.com.alura.comex.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

  private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory(
      "gf8xx75icz5iuaoi");
  /* TODO: alterar aqui */

  public static EntityManager getEntityManager() {
    return FACTORY.createEntityManager();
  }
}
