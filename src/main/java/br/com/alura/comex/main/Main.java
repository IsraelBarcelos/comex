package br.com.alura.comex.main;

import br.com.alura.comex.cli.menu.Menu;
import br.com.alura.services.processadores.Processador;
import br.com.alura.services.processadores.ProcessadorDeJSON;

// import java.net.URISyntaxException;

public class Main {

  public static void main(String[] args) {
    Processador processador = new ProcessadorDeJSON("pedidos.json");
    // posso tentar implementar o menu como uma chain of responsability
    new Menu(processador);
  }
}
