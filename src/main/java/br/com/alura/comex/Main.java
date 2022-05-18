package br.com.alura.comex;

import br.com.alura.services.Fechamento;
import br.com.alura.services.ProcessadorDeCSVOpenCSV;
import br.com.alura.services.ProcessadorDeCsv;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

  public static void main(String[] args) {
    // ProcessadorDeCsv listas = new ProcessadorDeCsv("pedidos.csv");
    ProcessadorDeCSVOpenCSV listas = new ProcessadorDeCSVOpenCSV("pedidos.csv");

    try {
      listas.execute();
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
    //   try {
    //     listas.execute();
    //   } catch (IOException e) {
    //     e.printStackTrace();
    //   } catch (URISyntaxException e) {
    //     e.printStackTrace();
    //   }

    new Fechamento(
      new PedidosDeUmFechamento(listas.getPedidos()),
      listas.getClientesDoSistema()
    );
  }
}
