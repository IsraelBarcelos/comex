package br.com.alura.comex.dto;

import br.com.alura.comex.models.Usuario;

public class UsuarioDto {
    String usuario;

    public UsuarioDto(Usuario usuario) {
        this.usuario = usuario.getEmail();
    }

    public String getUsuario() {
        return usuario;
    }

    public static UsuarioDto converter(Usuario usuario) {
        return new UsuarioDto(usuario);
    }

}
