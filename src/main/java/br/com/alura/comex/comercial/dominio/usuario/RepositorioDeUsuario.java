package br.com.alura.comex.comercial.dominio.usuario;

import java.util.Optional;

public interface RepositorioDeUsuario {
    void criarUsuario(Usuario usuario);

    Optional<Usuario> encontrarUsuarioPeloId(Long id);

    Optional<Usuario> encontrarUsuarioPeloEmail(String email);

    void removerUsuario(Usuario usuario);
}
