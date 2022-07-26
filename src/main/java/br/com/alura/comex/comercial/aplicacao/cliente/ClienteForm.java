package br.com.alura.comex.comercial.aplicacao.cliente;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.comex.comercial.dominio.cliente.Cliente;
import br.com.alura.comex.comercial.dominio.cliente.Endereco;
import br.com.alura.comex.comercial.dominio.cliente.Telefone;
import br.com.alura.comex.comercial.dominio.usuario.Usuario;
import br.com.alura.comex.comercial.infra.usuario.UsuarioRepository;

public class ClienteForm {

    @NotNull
    @NotEmpty
    @Length(min = 2)
    private String nome;
    @NotNull
    @NotEmpty
    private String cpf;
    @NotNull
    @NotEmpty
    private String telefone;
    @NotNull
    @NotEmpty
    private String rua;
    @NotNull
    private Integer numero;
    private String complemento;
    @NotNull
    @NotEmpty
    private String bairro;
    @NotNull
    @NotEmpty
    private String cidade;
    @NotNull
    @NotEmpty
    private String estado;

    @NotNull
    @NotEmpty
    private Integer ddd;

    @NotNull
    @NotEmpty
    private Integer numeroTelefone;

    @NotNull
    @NotEmpty
    private String usuarioEmail;

    public Cliente converter(UsuarioRepository usuarioRepository) {
        Endereco endereco = new EnderecoBuilder()
                .comRua(rua)
                .comNumero(numero)
                .comComplemento(complemento)
                .comBairro(bairro)
                .comCidade(cidade)
                .comEstado(estado)
                .build();

        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioEmail);

        if (!usuario.isPresent()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        return new ClienteBuilder()
                .comNome(nome)
                .comCpf(cpf)
                .comTelefone(new Telefone(ddd, numeroTelefone))
                .comEndereco(endereco)
                .comUsuario(usuario.get())
                .build();
    }
}
