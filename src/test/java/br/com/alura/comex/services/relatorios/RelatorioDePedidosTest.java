package br.com.alura.comex.services.relatorios;

import br.com.alura.comex.Cliente;
import br.com.alura.comex.Pedido;
import br.com.alura.comex.Produto;
import br.com.alura.services.Fechamento;
import br.com.alura.services.processadores.Processador;
import br.com.alura.services.processadores.ProcessadorDeCsv;
import br.com.alura.services.processadores.ProcessadorDeLista;
import br.com.alura.services.relatorios.pedidos.ProdutoMaisVendidoComLimit;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class RelatorioDePedidosTest {

  Processador processadorDeCsv = new ProcessadorDeCsv("pedidos.csv");
  Processador processadorDeListaVazia = new ProcessadorDeLista("");

  public String getSaidaPadrao() {
    return (
      "PRODUTO: iPhone 13 Pro\n" +
      "QUANTIDADE: 6\n" +
      "\n" +
      "PRODUTO: Galaxy S22 Ultra\n" +
      "QUANTIDADE: 5\n" +
      "\n" +
      "PRODUTO: Galaxy Tab S8\n" +
      "QUANTIDADE: 4\n" +
      "\n"
    );
  }

  @Test
  public void verificaSaidaComProcessadorCSVDoProdutoMaisVendido()
    throws IOException, URISyntaxException {
    String resultado = new ProdutoMaisVendidoComLimit()
    .geraSaida(new Fechamento(processadorDeCsv));
    Assert.assertEquals(getSaidaPadrao(), resultado);
  }

  @Test
  public void verificaSaidaComProcessadorComItemVazio()
    throws IOException, URISyntaxException {
    String resultado = new ProdutoMaisVendidoComLimit()
    .geraSaida(new Fechamento(processadorDeListaVazia));
    Assert.assertEquals("", resultado);
  }

  @Test
  public void verificaSaidaComProcessadorDeCsvComUmItem()
    throws IOException, URISyntaxException {
    ArrayList<Pedido> listaDePedidos = new ArrayList<>();
    listaDePedidos.add(
      new Pedido(
        new Produto("teste", "teste", BigDecimal.valueOf(560.00)),
        new Cliente("Jose"),
        1,
        LocalDate.now()
      )
    );
    Processador processadorDeLista = new ProcessadorDeLista(listaDePedidos, "");
    String resultado = new ProdutoMaisVendidoComLimit()
    .geraSaida(new Fechamento(processadorDeLista));
    Assert.assertEquals(
      "PRODUTO: teste\n" + "QUANTIDADE: 1\n" + "\n",
      resultado
    );
  }
}
