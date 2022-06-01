package br.com.alura.services.processadores;

import br.com.alura.comex.Pedido;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ProcessadorDeLista extends Processador {

  public ProcessadorDeLista(String path) {
    super("");
  }

  public ProcessadorDeLista(ArrayList<Pedido> listaDePedidos, String path)
    throws IOException, URISyntaxException {
    super("");
    this.pedidos = listaDePedidos;
  }

  @Override
  public void execute() throws IOException, URISyntaxException {
    pedidos.forEach(
      pedido -> {
        this.clientesDoSistema.adicionaNovoClienteOuRejeita(
            pedido.getCliente().getNome()
          );
      }
    );
  }
}
