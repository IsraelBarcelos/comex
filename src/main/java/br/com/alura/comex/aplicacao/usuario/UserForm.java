package br.com.alura.comex.aplicacao.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import br.com.alura.comex.dominio.usuario.Usuario;
import br.com.alura.comex.dominio.usuario.UsuarioBuilder;

public class UserForm {

    @NotNull
    @Email
    private String email;
    @NotNull
    private String senha;
    @NotNull
    private String nome;

    public UserForm() {

    }

    public UserForm(String email, String senha, String nome) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario converter() {
        return new UsuarioBuilder()
                .comEmail(email)
                .comSenha(senha)
                .comNome(nome)
                .build();
    }
}
