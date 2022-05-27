package br.com.alura.comex.main;

import br.com.alura.comex.main.menu.MenuEnum;
import br.com.alura.services.Fechamento;
import br.com.alura.services.processadores.Processador;
import br.com.alura.services.processadores.ProcessadorDeJSON;
import br.com.alura.services.relatorios.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// import java.net.URISyntaxException;

public class Main {

  public static void menu(Processador processador, String opt)
    throws IOException, URISyntaxException {
    Fechamento fechamento = new Fechamento(processador);

    if (fechamento.getPedidosDeUmFechamento().getPedidos().isEmpty()) {
      System.out.println("Nenhum pedido foi realizado!");
      return;
    }

    MenuEnum menuEnumInstance = MenuEnum.valueOf(opt);
    Relatorio relatorio = menuEnumInstance.getRelatorio();

    switch (opt) {
      case "SINTETICO":
        new RelatorioSintetico(fechamento);
        break;
      case "CLIENTES":
        new RelatoriosDeCliente(fechamento);
        break;
      case "PEDIDOS":
        new RelatorioDePedidos(fechamento);
        break;
      case "CATEGORIAS":
        new RelatoriosDeCategoria(fechamento);
        break;
      default:
        break;
    }
  }

  public static void main(String[] args) {
    // ProcessadorDeCsv listas = new ProcessadorDeCsv("pedidos.csv");

    // ProcessadorDeJSON processador = new ProcessadorDeJSON("pedidos.json");
    // ProcessadorDeXML processador = new ProcessadorDeXML("pedidos.json");
    // try {
    //   processador.execute();
    // } catch (IOException e) {
    //   e.printStackTrace();
    // }

    // ProcessadorDeCSVOpenCSV listas = new ProcessadorDeCSVOpenCSV("pedidos.csv");

    // try {
    //   listas.execute();
    // } catch (IOException | URISyntaxException e) {
    //   e.printStackTrace();
    // }

    //   try {
    //     listas.execute();
    //   } catch (IOException e) {
    //     e.printStackTrace();
    //   } catch (URISyntaxException e) {
    //     e.printStackTrace();
    //   }

    Processador processador = new ProcessadorDeJSON("pedidos.json");

    try {
      Scanner scanner = new Scanner(System.in);

      Set<String> set = new HashSet<>();
      set.add("SINTETICO");
      set.add("CLIENTES");
      set.add("PEDIDOS");
      set.add("CATEGORIAS");
      String opt;
      do {
        System.out.println("\nSINTETICO - Relatório Sintético");
        System.out.println("CLIENTES - Relatório de Clientes");
        System.out.println("PEDIDOS - Relatório de Pedidos");
        System.out.println("CATEGORIAS - Relatório de Categorias");
        System.out.println("Qualquer outra opção fechará o programa");
        opt = scanner.nextLine();
        Main.menu(processador, opt);
      } while (set.contains(opt));

      scanner.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
