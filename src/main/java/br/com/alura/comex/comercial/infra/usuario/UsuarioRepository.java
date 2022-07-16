package br.com.alura.comex.comercial.infra.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.comex.comercial.dominio.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
