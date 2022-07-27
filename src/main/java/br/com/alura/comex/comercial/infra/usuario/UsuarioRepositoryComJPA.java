package br.com.alura.comex.comercial.infra.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.comex.comercial.dominio.usuario.RepositorioDeUsuario;
import br.com.alura.comex.comercial.dominio.usuario.Usuario;

public interface UsuarioRepositoryComJPA extends RepositorioDeUsuario, JpaRepository<Usuario, Long> {

    default void criarUsuario(Usuario usuario) {
        this.save(usuario);
    }

    @Query("select u from Usuario u where u.email.endereco = ?1")
    Optional<Usuario> encontrarUsuarioPeloEmail(String email);

    @Query("select u from Usuario u where u.id = ?1")
    Optional<Usuario> encontrarUsuarioPeloId(Long id);

    default void removerUsuario(Usuario usuario) {
        this.delete(usuario);
    }

}
