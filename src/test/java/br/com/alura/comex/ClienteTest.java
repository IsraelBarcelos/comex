package br.com.alura.comex;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class ClienteTest {

    private final Cliente cliente = new Cliente("Fulano");



    @Test
    public void deveAdicionarUmClienteComValorDeVendasTotais0() {
        assertEquals(BigDecimal.ZERO, cliente.getTotalDeCompras());
    }

    @Test
    public void deveTerApenasUmPedido() {
        assertEquals(1, cliente.getNumeroDePedidos());
    }

    @Test
    public void deveTerMaisPedidos() {
        cliente.addPedido();
        assertEquals(2, cliente.getNumeroDePedidos());
    }

    @Test
    public void deveAdicionarValorNaCompra() {
        cliente.adicionaNovaCompra(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, cliente.getTotalDeCompras());
    }

    @Test
    public void deveRetornarQueNumeroDePedidosNaoEMaiorQue2() {
        boolean naoEMaiorQue2 = cliente.getNumeroDePedidos() < 2;
        assertEquals(true, naoEMaiorQue2);
    }
}
