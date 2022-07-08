package br.com.alura.comex.controllers.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alura.comex.models.Perfil;

public class AddProfileForm {

    @NotNull
    private String nomePerfil;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    public AddProfileForm() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomePerfil() {
        return nomePerfil;
    }

    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }

    public Perfil converter() {
        Perfil perfil = new Perfil();
        perfil.setNome(nomePerfil);
        return perfil;
    }

}
