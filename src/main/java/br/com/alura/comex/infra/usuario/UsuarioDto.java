package br.com.alura.comex.infra.usuario;

import br.com.alura.comex.dominio.usuario.Usuario;

public class UsuarioDto {
    String email;

    public UsuarioDto(Usuario usuario) {
        this.email = usuario.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public static UsuarioDto converter(Usuario usuario) {
        return new UsuarioDto(usuario);
    }

}
