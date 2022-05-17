package br.com.alura.comex;

import java.util.ArrayList;
import java.util.Collection;

public class ClientesDoSistema {

  private ArrayList<Cliente> clientes;

  public void add(Cliente cliente) {
    clientes.add(cliente);
  }

  public Collection<Cliente> getClientesDoSistema() {
    return this.clientes;
  }
}
