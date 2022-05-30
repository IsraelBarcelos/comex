package br.com.alura.comex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class CategoriasProcessadasTest {

    CategoriasProcessadas categoriasProcessadas = new CategoriasProcessadas(
        Arrays.asList(
            new Pedido(
                new Produto("carro", "veiculos", BigDecimal.valueOf(65000.00)), 
                new Cliente("Joao"), 
                1, 
                LocalDate.now()),
                new Pedido(
                new Produto("carro", "veiculos", BigDecimal.valueOf(60000.00)),
                new Cliente("Ana"),
                1,
                LocalDate.now())
                )
    );

    @Test
    public void receberAnaNoArraySortado() {
        Assert.assertEquals("Ana", categoriasProcessadas.getSortedCategorias().get(0));
    }
}
