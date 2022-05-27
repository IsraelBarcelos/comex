package br.com.alura.services.execucao.menu;

import br.com.alura.services.Fechamento;
import br.com.alura.services.processadores.Processador;
import br.com.alura.services.relatorios.Relatorio;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Menu {

  private Set<String> set = new HashSet<>();
  private String opt;
  private Processador processador;

  public Menu(Processador processador) {
    this.processador = processador;
    set.add("SINTETICO");
    set.add("CLIENTES");
    set.add("PEDIDOS");
    set.add("CATEGORIAS");
    inicializaMenu();
  }

  private final void inicializaMenu() {
    try {
      Scanner scanner = new Scanner(System.in);
      do {
        new DescricaoDoMenu();
        opt = scanner.nextLine();
        menu(processador, opt);
      } while (set.contains(opt));

      scanner.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private final void menu(Processador processador, String opt)
    throws IOException, URISyntaxException {
    {
      Fechamento fechamento = new Fechamento(processador);

      if (fechamento.getPedidosDeUmFechamento().getPedidos().isEmpty()) {
        System.out.println("Nenhum pedido foi realizado!");
        return;
      }

      Relatorio relatorio = MenuEnum.valueOf(opt).getRelatorio(fechamento);
      relatorio.imprimirRelatorios();
    }
  }
}
