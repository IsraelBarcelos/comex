package br.com.alura.comex.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Value;

public class JPAUtil {

  @Value("${DB_NAME}")
  private static String databaseName;

  private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory(
      databaseName);

  public static EntityManager getEntityManager() {
    return FACTORY.createEntityManager();
  }
}
