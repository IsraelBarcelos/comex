package br.com.alura.comex.aplicacao.processadores;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ProcessadorInterface {
  void execute() throws IOException, URISyntaxException;
}
