package br.com.alura.comex.comercial.dominio.usuario;

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

    public UsuarioBuilder addPerfil(Perfil perfil) {
        this.usuario.addPerfil(perfil);
        return this;
    }

    public Usuario build() {
        return this.usuario;
    }

}
