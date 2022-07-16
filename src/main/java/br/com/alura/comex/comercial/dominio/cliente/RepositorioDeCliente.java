package br.com.alura.comex.comercial.dominio.cliente;

import java.util.List;
import java.util.Optional;

public interface RepositorioDeCliente {

    void adicionarCliente(Cliente cliente);

    Optional<Cliente> encontrarClientePeloNome(String nomeCliente);

    Optional<Cliente> encontrarPeloCpf(Cpf cpf);

    List<Cliente> listarTodosAlunosCadastrados();
}
