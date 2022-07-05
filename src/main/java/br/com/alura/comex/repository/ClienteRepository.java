package br.com.alura.comex.repository;

import br.com.alura.comex.models.Cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  Optional<Cliente> findByNome(String nomeDoCliente);
}
