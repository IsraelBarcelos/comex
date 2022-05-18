package br.com.alura.services;

import br.com.alura.comex.Cliente;
import br.com.alura.comex.ClientesDoSistema;
import br.com.alura.comex.Pedido;
import br.com.alura.comex.Produto;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcessadorDeCsv implements ResgatarRelatoriosInterface {

  private String path;
  private ArrayList<Pedido> pedidos = new ArrayList<>();
  private ClientesDoSistema clientesDoSistema;
  private Cliente clientePlaceholder;

  public ProcessadorDeCsv(String path) {
    this.path = path;
    this.clientesDoSistema = new ClientesDoSistema();
  }

  public void execute() throws IOException, URISyntaxException {
    URL recursoCSV = ClassLoader.getSystemResource(this.path);
    Path caminhoDoArquivo = Path.of(recursoCSV.toURI());

    Scanner leitorDeLinhas = new Scanner(caminhoDoArquivo);

    leitorDeLinhas.nextLine();

    this.transformarDadosEmLinhas(leitorDeLinhas);

    leitorDeLinhas.close();
  }

  public ArrayList<Pedido> getPedidos() {
    return pedidos;
  }

  public ClientesDoSistema getClientesDoSistema() {
    return clientesDoSistema;
  }

  private void transformarDadosEmLinhas(Scanner leitorDeLinhas) {
    while (leitorDeLinhas.hasNextLine()) {
      String linha = leitorDeLinhas.nextLine();
      String[] registro = linha.split(",");

      String categoria = registro[0];
      String produto = registro[1];
      BigDecimal preco = new BigDecimal(registro[2]);
      int quantidade = Integer.parseInt(registro[3]);
      LocalDate data = LocalDate.parse(
        registro[4],
        DateTimeFormatter.ofPattern("dd/MM/yyyy")
      );
      String cliente = registro[5];

      if (
        clientesDoSistema
          .getClientesDoSistema()
          .stream()
          .noneMatch(c -> c.getNome().equals(cliente))
      ) {
        this.clientePlaceholder = new Cliente(cliente);
        clientesDoSistema.add(this.clientePlaceholder);
      } else {
        clientePlaceholder =
          clientesDoSistema
            .getClientesDoSistema()
            .stream()
            .filter(c -> c.getNome().equals(cliente))
            .findFirst()
            .get();

        clientePlaceholder.addPedido();
      }

      Pedido pedido = new Pedido(
        new Produto(produto, categoria, preco),
        clientePlaceholder,
        quantidade,
        data
      );
      pedidos.add(pedido);
    }
  }
}
