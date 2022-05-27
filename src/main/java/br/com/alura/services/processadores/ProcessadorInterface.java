package br.com.alura.services.processadores;

import br.com.alura.comex.ClientesDoSistema;
import br.com.alura.comex.Pedido;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public interface ProcessadorInterface {
  void execute() throws IOException, URISyntaxException;

  ArrayList<Pedido> getPedidos();

  ClientesDoSistema getClientesDoSistema();
}
