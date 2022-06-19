package br.com.alura.comex.repository;

import br.com.alura.comex.models.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
  Cliente findByNome(String nomeDoCliente);
}
