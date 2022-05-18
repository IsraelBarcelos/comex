package br.com.alura.comex;

import java.util.ArrayList;
import java.util.Collection;

public class ClientesDoSistema {

  private ArrayList<Cliente> clientes;

  public ClientesDoSistema() {
    this.clientes = new ArrayList<>();
  }

  public void add(Cliente cliente) {
    clientes.add(cliente);
  }

  public Collection<Cliente> getClientesDoSistema() {
    return this.clientes;
  }

  public Cliente adicionaNovoClienteOuRejeita(String nomeDoCliente) {
    Cliente clientePlaceholder;
    if (
      this.clientes.stream()
        .noneMatch(
          clienteDaLista -> clienteDaLista.getNome().equals(nomeDoCliente)
        )
    ) {
      clientePlaceholder = new Cliente(nomeDoCliente);
      this.clientes.add(clientePlaceholder);
      return clientePlaceholder;
    }

    clientePlaceholder =
      this.clientes.stream()
        .filter(c -> c.getNome().equals(nomeDoCliente))
        .findFirst()
        .get();

    clientePlaceholder.addPedido();

    return clientePlaceholder;
  }
}
