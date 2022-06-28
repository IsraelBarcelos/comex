package br.com.alura.comex.builders;

import br.com.alura.comex.models.Usuario;

public class UsuarioBuilder {
    private Usuario usuario = new Usuario();

    public UsuarioBuilder comEmail(String email) {
        this.usuario.setEmail(email);
        return this;
    }

    public UsuarioBuilder comSenha(String senha) {
        this.usuario.setSenha(senha);
        return this;
    }

    public UsuarioBuilder comNome(String nome) {
        this.usuario.setNome(nome);
        return this;
    }

    public Usuario build() {
        return this.usuario;
    }

}
