package br.com.alura.comex.aplicacao.cliente;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.comex.dominio.cliente.Cliente;
import br.com.alura.comex.dominio.cliente.ClienteBuilder;
import br.com.alura.comex.dominio.cliente.Endereco;
import br.com.alura.comex.dominio.cliente.EnderecoBuilder;
import br.com.alura.comex.dominio.cliente.Telefone;
import br.com.alura.comex.infra.usuario.UsuarioRepository;

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

    public ClienteForm() {
    }

    public Cliente converter(UsuarioRepository usuarioRepository) {
        Endereco endereco = new EnderecoBuilder()
                .comRua(rua)
                .comNumero(numero)
                .comComplemento(complemento)
                .comBairro(bairro)
                .comCidade(cidade)
                .comEstado(estado)
                .build();
        Cliente cliente = new ClienteBuilder()
                .comNome(nome)
                .comCpf(cpf)
                .comTelefone(new Telefone(ddd, numeroTelefone))
                .comEndereco(endereco)
                .comUsuario(usuarioRepository.findByEmail(usuarioEmail).get())
                .build();

        return cliente;
    }
}
