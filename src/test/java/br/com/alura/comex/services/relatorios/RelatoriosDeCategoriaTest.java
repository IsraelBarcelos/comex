package br.com.alura.comex.services.relatorios;

import br.com.alura.comex.Cliente;
import br.com.alura.comex.Pedido;
import br.com.alura.comex.Produto;
import br.com.alura.services.Fechamento;
import br.com.alura.services.processadores.Processador;
import br.com.alura.services.processadores.ProcessadorDeCsv;
import br.com.alura.services.processadores.ProcessadorDeJSON;
import br.com.alura.services.processadores.ProcessadorDeLista;
import br.com.alura.services.processadores.ProcessadorDeXML;
import br.com.alura.services.relatorios.categorias.ProdutosMaisCaros;
import br.com.alura.services.relatorios.categorias.VendasPorCategorias;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import org.junit.Assert;
import org.junit.Test;

public class RelatoriosDeCategoriaTest {

  Processador processadorDeCsv = new ProcessadorDeCsv("pedidos.csv");
  Processador processadorDeListaVazia = new ProcessadorDeLista("");
  Locale ptbr = new Locale("pt", "BR");

  public String getSaidaPadrao() {
    return (
      "CATEGORIA: AUTOMOTIVA\n" +
      "QUANTIDADE VENDIDA: 2\n" +
      "MONTANTE: " +
      NumberFormat.getCurrencyInstance(ptbr).format(1987.97) +
      "\n" +
      "CATEGORIA: CELULARES\n" +
      "QUANTIDADE VENDIDA: 11\n" +
      "MONTANTE: " +
      NumberFormat.getCurrencyInstance(ptbr).format(97801.50) +
      "\n" +
      "CATEGORIA: INFORMÁTICA\n" +
      "QUANTIDADE VENDIDA: 9\n" +
      "MONTANTE: " +
      NumberFormat.getCurrencyInstance(ptbr).format(64698.40) +
      "\n" +
      "CATEGORIA: LIVROS\n" +
      "QUANTIDADE VENDIDA: 9\n" +
      "MONTANTE: " +
      NumberFormat.getCurrencyInstance(ptbr).format(1507.64) +
      "\n" +
      "CATEGORIA: MÓVEIS\n" +
      "QUANTIDADE VENDIDA: 4\n" +
      "MONTANTE: " +
      NumberFormat.getCurrencyInstance(ptbr).format(12378.98) +
      "\n"
    );
  }

  public String getSaidaProdutoMaisCaro() {
    return (
      "\nCATEGORIA: AUTOMOTIVA\n" +
      "PRODUTO: Jogo de pneus\n" +
      "PREÇO: " +
      NumberFormat.getCurrencyInstance(ptbr).format(1276.79) +
      "\n" +
      "CATEGORIA: CELULARES\n" +
      "PRODUTO: iPhone 13 Pro\n" +
      "PREÇO: " +
      NumberFormat.getCurrencyInstance(ptbr).format(9176.00) +
      "\n" +
      "CATEGORIA: INFORMÁTICA\n" +
      "PRODUTO: Macbook Pro 16\n" +
      "PREÇO: " +
      NumberFormat.getCurrencyInstance(ptbr).format(31752.00) +
      "\n" +
      "CATEGORIA: LIVROS\n" +
      "PRODUTO: Building Microservices\n" +
      "PREÇO: " +
      NumberFormat.getCurrencyInstance(ptbr).format(300.28) +
      "\n" +
      "CATEGORIA: MÓVEIS\n" +
      "PRODUTO: Mesa de jantar 6 lugares\n" +
      "PREÇO: " +
      NumberFormat.getCurrencyInstance(ptbr).format(3678.98) +
      "\n"
    );
  }

  @Test
  public void verificaSaidaComProcessadorCSV()
    throws IOException, URISyntaxException {
    String resultado = new VendasPorCategorias()
    .geraSaida(new Fechamento(processadorDeCsv));
    Assert.assertEquals(getSaidaPadrao(), resultado);
  }

  @Test
  public void verificaSaidaComProcessadorJSON()
    throws IOException, URISyntaxException {
    String resultado = new VendasPorCategorias()
    .geraSaida(new Fechamento(new ProcessadorDeJSON("pedidos.json")));
    Assert.assertEquals(getSaidaPadrao(), resultado);
  }

  @Test
  public void verificaSaidaComProcessadorXML()
    throws IOException, URISyntaxException {
    String resultado = new VendasPorCategorias()
    .geraSaida(new Fechamento(new ProcessadorDeXML("pedidos.xml")));
    Assert.assertEquals(getSaidaPadrao(), resultado);
  }

  @Test
  public void verificaSaidaComProcessadorComItemVazio()
    throws IOException, URISyntaxException {
    String resultado = new VendasPorCategorias()
    .geraSaida(new Fechamento(processadorDeListaVazia));
    Assert.assertEquals("", resultado);
  }

  @Test
  public void verificaSaidaComProcessadorDeCsvDoProdutoMaisCaroDeCadaCategoria()
    throws IOException, URISyntaxException {
    String resultado = new ProdutosMaisCaros()
    .geraSaida(new Fechamento(processadorDeCsv));
    Assert.assertEquals(getSaidaProdutoMaisCaro(), resultado);
  }

  @Test
  public void verificaSaidaComProcessadorDeListaComUmItem()
    throws IOException, URISyntaxException {
    ArrayList<Pedido> listaDePedidos = new ArrayList<>();
    listaDePedidos.add(
      new Pedido(
        new Produto("teste", "testeCategoria", BigDecimal.valueOf(560.00)),
        new Cliente("Jose"),
        1,
        LocalDate.now()
      )
    );
    Processador processadorDeLista = new ProcessadorDeLista(listaDePedidos, "");
    String resultado = new ProdutosMaisCaros()
    .geraSaida(new Fechamento(processadorDeLista));
    Assert.assertEquals(
      "\nCATEGORIA: testeCategoria\n" +
      "PRODUTO: teste\n" +
      "PREÇO: R$ 560,00\n",
      resultado
    );
  }
}
