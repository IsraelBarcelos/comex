package br.com.alura.comex.controllers.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alura.comex.repository.UsuarioRepository;
import org.hibernate.validator.constraints.Length;

import br.com.alura.comex.builders.ClienteBuilder;
import br.com.alura.comex.builders.EnderecoBuilder;
import br.com.alura.comex.models.Cliente;
import br.com.alura.comex.models.Endereco;
import br.com.alura.comex.models.Telefone;

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
