package br.com.alura.services.processadores;

import br.com.alura.comex.Pedido;
import br.com.alura.comex.Produto;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessadorDeCSVOpenCSV extends Processador {

  List<String[]> linhas;

  public ProcessadorDeCSVOpenCSV(String path) {
    super(path);
    this.linhas = new ArrayList<String[]>();
  }

  public void execute() throws IOException, URISyntaxException {
    URL recursoCSV = ClassLoader.getSystemResource(this.path);
    CSVReader csvReader = new CSVReader(new FileReader(recursoCSV.getPath()));
    this.getAll(csvReader);
    csvReader.close();
  }

  public void getAll(CSVReader reader) {
    try {
      List<Map<String, String>> lines = new ArrayList<Map<String, String>>();
      String[] headers = reader.readNext();
      String[] cols = null;

      while ((cols = reader.readNext()) != null) {
        Map<String, String> fields = new HashMap<String, String>();
        for (int i = 0; i < headers.length; i++) {
          fields.put(headers[i], cols[i]);
        }
        lines.add(fields);
      }

      lines.forEach(
        colsInLine -> {
          LocalDate data = LocalDate.parse(
            colsInLine.get("DATA"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
          );
          String nomeDoCliente = colsInLine.get("CLIENTE");

          this.clientePlaceholder =
            this.clientesDoSistema.adicionaNovoClienteOuRejeita(nomeDoCliente);

          Pedido pedido = new Pedido(
            new Produto(
              colsInLine.get("PRODUTO"),
              colsInLine.get("CATEGORIA"),
              new BigDecimal(colsInLine.get("PRECO"))
            ),
            clientePlaceholder,
            Integer.parseInt(colsInLine.get("QUANTIDADE")),
            data
          );
          pedidos.add(pedido);
        }
      );
    } catch (CsvValidationException | IOException e) {
      e.printStackTrace();
    }
  }
}
