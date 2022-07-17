package br.com.alura.comex.comercial.infra.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.comex.comercial.dominio.cliente.Cliente;
import br.com.alura.comex.comercial.dominio.cliente.RepositorioDeCliente;
import br.com.alura.comex.shared.dominio.Cpf;

public interface ClienteRepositoryComJPA extends RepositorioDeCliente, JpaRepository<Cliente, Long> {

  default void adicionarCliente(Cliente cliente) {
    this.save(cliente);
  }

  @Query("select c from Cliente c where c.nome = ?1")
  Optional<Cliente> encontrarClientePeloNome(String nomeCliente);

  @Query("select c from Cliente c where c.cpf.numeroCpf = ?1")
  Optional<Cliente> encontrarPeloCpf(Cpf cpf);

  @Query("select c from Cliente c")
  List<Cliente> listarTodosAlunosCadastrados();

}
