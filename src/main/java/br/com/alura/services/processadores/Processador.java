package br.com.alura.services.processadores;

import br.com.alura.comex.Cliente;
import br.com.alura.comex.ClientesDoSistema;
import br.com.alura.comex.Pedido;
import java.util.ArrayList;

public abstract class Processador implements ProcessadorInterface {

  protected String path;
  protected ArrayList<Pedido> pedidos = new ArrayList<>();
  protected ClientesDoSistema clientesDoSistema;
  protected Cliente clientePlaceholder;

  public Processador(String path) {
    this.path = path;
    this.clientesDoSistema = new ClientesDoSistema();
  }

  @Override
  public ArrayList<Pedido> getPedidos() {
    return this.pedidos;
  }

  @Override
  public ClientesDoSistema getClientesDoSistema() {
    return this.clientesDoSistema;
  }

  public Processador getProcessador() {
    return this;
  }
}
