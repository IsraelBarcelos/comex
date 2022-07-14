package br.com.alura.comex.infra.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.comex.dominio.cliente.Cliente;
import br.com.alura.comex.dominio.cliente.ClienteNaoEncontrado;
import br.com.alura.comex.dominio.cliente.Cpf;

public interface ClienteRepositoryComJPA extends JpaRepository<Cliente, Long> {

  Optional<Cliente> findByNome(String nomeDoCliente);

  @Query("select c from Cliente c where c.cpf.numeroCpf = ?1")
  Optional<Cliente> findByNumeroCpf(String cpf);

  default void adicionarCliente(Cliente cliente) {
    this.save(cliente);
  }

  default Cliente encontrarClientePeloNome(String nomeCliente) {
    Optional<Cliente> cliente = this.findByNome(nomeCliente);
    if (!cliente.isPresent()) {
      throw new ClienteNaoEncontrado(nomeCliente);
    }
    return cliente.get();
  }

  default Cliente encontrarPeloCpf(Cpf cpf) {
    Optional<Cliente> cliente = this.findByNumeroCpf(cpf.getNumeroCpf());
    if (!cliente.isPresent()) {
      throw new ClienteNaoEncontrado(cpf);
    }
    return cliente.get();
  }

  default List<Cliente> listarTodosAlunosCadastrados() {
    return this.findAll();
  }

}
