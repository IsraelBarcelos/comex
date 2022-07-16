package br.com.alura.comex.comercial.infra.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.comex.comercial.dominio.usuario.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Optional<Perfil> getByNome(String string);

}
