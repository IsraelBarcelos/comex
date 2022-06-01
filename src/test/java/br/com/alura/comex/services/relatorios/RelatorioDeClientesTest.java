package br.com.alura.comex.services.relatorios;

import br.com.alura.comex.Cliente;
import br.com.alura.comex.Pedido;
import br.com.alura.comex.Produto;
import br.com.alura.services.Fechamento;
import br.com.alura.services.processadores.Processador;
import br.com.alura.services.processadores.ProcessadorDeCsv;
import br.com.alura.services.processadores.ProcessadorDeLista;
import br.com.alura.services.relatorios.clientes.ClientesFieis;
import br.com.alura.services.relatorios.clientes.ClientesMaisLucrativos;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import org.junit.Assert;
import org.junit.Test;

public class RelatorioDeClientesTest {

  Processador processadorDeCsv = new ProcessadorDeCsv("pedidos.csv");
  Processador processadorDeListaVazia = new ProcessadorDeLista("");
  Locale ptbr = new Locale("pt", "BR");

  public String getSaidaPadrao() {
    return (
      "NOME: ANA\n" +
      "Nº DE PEDIDOS: 4\n" +
      "\n" +
      "NOME: BIA\n" +
      "Nº DE PEDIDOS: 3\n" +
      "\n" +
      "NOME: CAIO\n" +
      "Nº DE PEDIDOS: 3\n" +
      "\n" +
      "NOME: DANI\n" +
      "Nº DE PEDIDOS: 4\n" +
      "\n" +
      "NOME: ELI\n" +
      "Nº DE PEDIDOS: 1\n" +
      "\n" +
      "NOME: GABI\n" +
      "Nº DE PEDIDOS: 1\n" +
      "\n"
    );
  }

  @Test
  public void verificaSaidaComProcessadorCSVDoRelatorioDeClientesFieis()
    throws IOException, URISyntaxException {
    String resultado = new ClientesFieis()
    .geraSaida(new Fechamento(processadorDeCsv));
    Assert.assertEquals(getSaidaPadrao(), resultado);
  }

  @Test
  public void verificaSaidaCom3PedidosDoMesmoClienteParaClientesFieis()
    throws IOException, URISyntaxException {
    ArrayList<Pedido> listaDePedidos = new ArrayList<>();
    listaDePedidos.add(
      new Pedido(
        new Produto("teste 1", "testeCategoria", BigDecimal.valueOf(560.00)),
        new Cliente("Jose"),
        1,
        LocalDate.now()
      )
    );

    listaDePedidos.add(
      new Pedido(
        new Produto("teste 2", "testeCategoria", BigDecimal.valueOf(560.00)),
        new Cliente("Jose"),
        1,
        LocalDate.now()
      )
    );

    listaDePedidos.add(
      new Pedido(
        new Produto("teste 3", "testeCategoria", BigDecimal.valueOf(560.00)),
        new Cliente("Jose"),
        1,
        LocalDate.now()
      )
    );

    Processador processadorDeLista = new ProcessadorDeLista(listaDePedidos, "");

    String resultado = new ClientesFieis()
    .geraSaida(new Fechamento(processadorDeLista));

    Assert.assertEquals("NOME: Jose\nNº DE PEDIDOS: 3\n\n", resultado);
  }

  @Test
  public void verificaSaidaComProcessadorDeCsvDeClientesMaisLucrativos()
    throws IOException, URISyntaxException {
    String resultado = new ClientesMaisLucrativos()
    .geraSaida(new Fechamento(processadorDeCsv));
    Assert.assertEquals(
      "NOME: ANA\n" +
      "Nº DE PEDIDOS: 4\n" +
      "MONTANTE GASTO: " +
      NumberFormat.getCurrencyInstance(ptbr).format(61284.80) +
      "\n" +
      "NOME: DANI\n" +
      "Nº DE PEDIDOS: 4\n" +
      "MONTANTE GASTO: " +
      NumberFormat.getCurrencyInstance(ptbr).format(54786.40) +
      "\n",
      resultado
    );
  }
}
