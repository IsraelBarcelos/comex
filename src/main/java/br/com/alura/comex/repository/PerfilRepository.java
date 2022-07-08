package br.com.alura.comex.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.comex.models.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Optional<Perfil> getByNome(String string);

}
