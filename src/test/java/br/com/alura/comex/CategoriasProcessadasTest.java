package br.com.alura.comex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class CategoriasProcessadasTest {

  CategoriasProcessadas categoriasProcessadas = new CategoriasProcessadas(
    Arrays.asList(
      new Pedido(
        new Produto("carro", "veiculos", BigDecimal.valueOf(65000.00)),
        new Cliente("Joao"),
        1,
        LocalDate.now()
      ),
      new Pedido(
        new Produto("aviao", "aeroespacial", BigDecimal.valueOf(890000000.00)),
        new Cliente("Ana"),
        1,
        LocalDate.now()
      )
    )
  );

  @Test
  public void receberAnaNoArraySortado() {
    Assert.assertEquals(
      "aeroespacial",
      categoriasProcessadas.getSortedCategorias().get(0)
    );
  }

  @Test
  public void contarElementos() {
    Assert.assertEquals(2, categoriasProcessadas.getSortedCategorias().size());
  }

  @Test
  public void adicionarelementosEContar() {
    List<Pedido> novoElemento = Arrays.asList(
      new Pedido(
        new Produto("pano de chão", "tecelaria", BigDecimal.valueOf(65000.00)),
        new Cliente("Joao"),
        1,
        LocalDate.now()
      )
    );

    categoriasProcessadas.verificaSeEstaNaCategoriaEAdiciona(novoElemento);

    Assert.assertEquals(3, categoriasProcessadas.getSortedCategorias().size());
  }

  @Test
  public void adicionarelementosEContarElementoRepetido() {
    List<Pedido> novoElemento = Arrays.asList(
      new Pedido(
        new Produto("pano de chão", "tecelaria", BigDecimal.valueOf(65000.00)),
        new Cliente("Joao"),
        1,
        LocalDate.now()
      )
    );

    categoriasProcessadas.verificaSeEstaNaCategoriaEAdiciona(novoElemento);

    Assert.assertEquals(3, categoriasProcessadas.getSortedCategorias().size());
  }
}
