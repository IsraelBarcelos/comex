package br.com.alura.services;

import br.com.alura.comex.Cliente;
import br.com.alura.comex.ClientesDoSistema;
import br.com.alura.comex.Pedido;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class ProcessadorDeCsv implements ResgatarRelatoriosInterface {

  private String path;
  private ArrayList<Pedido> pedidos = new ArrayList<>();
  private ClientesDoSistema clientesDoSistema;

  public ProcessadorDeCsv(String path) {
    this.path = path;
    this.clientesDoSistema = new ClientesDoSistema();
  }

  public void execute() throws IOException, URISyntaxException {
    URL recursoCSV = ClassLoader.getSystemResource(this.path);
    Path caminhoDoArquivo = Path.of(recursoCSV.toURI());

    Scanner leitorDeLinhas = new Scanner(caminhoDoArquivo);

    leitorDeLinhas.nextLine();

    int quantidadeDeRegistros = 0;
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
        clientesDoSistema.add(new Cliente(cliente));
      }

      Pedido pedido = new Pedido(
        categoria,
        produto,
        cliente,
        preco,
        quantidade,
        data
      );
      pedidos.add(pedido);

      quantidadeDeRegistros++;
    }
  }

  public ArrayList<Pedido> getPedidos() {
    return pedidos;
  }
}
