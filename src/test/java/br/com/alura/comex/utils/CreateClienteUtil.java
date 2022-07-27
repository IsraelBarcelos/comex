package br.com.alura.comex.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.alura.comex.comercial.aplicacao.cliente.ClienteBuilder;
import br.com.alura.comex.comercial.aplicacao.cliente.EnderecoBuilder;
import br.com.alura.comex.comercial.dominio.cliente.Cliente;
import br.com.alura.comex.comercial.dominio.cliente.RepositorioDeCliente;
import br.com.alura.comex.comercial.dominio.cliente.Telefone;
import br.com.alura.comex.comercial.dominio.usuario.RepositorioDeUsuario;
import br.com.alura.comex.comercial.infra.usuario.PerfilRepository;
import br.com.alura.comex.shared.dominio.Cpf;

public class CreateClienteUtil {

    public final static String nome = "teste";
    public final static String cpf = "58648610060";
    public final static String telefone = "telefone";
    public final static String rua = "rua";
    public final static int numero = 123;
    public final static String complemento = "complemento";
    public final static String bairro = "bairro";
    public final static String cidade = "cidade";
    public final static String estado = "estado";
    public final static Integer ddd = 54;
    public final static Integer numeroTelefone = 123456789;

    public static void createCliente(RepositorioDeCliente clienteRepository,
            RepositorioDeUsuario usuarioRepository,
            PasswordEncoder passwordEncoder, PerfilRepository perfilRepository)
            throws Exception {
        if (clienteRepository.encontrarClientePeloNome(nome).isPresent()) {
            return;
        }

        CreateUserUtil.createUser(usuarioRepository, passwordEncoder, perfilRepository);

        Cliente cliente = new ClienteBuilder()
                .comNome(nome)
                .comCpf(new Cpf(cpf))
                .comTelefone(new Telefone(ddd, numeroTelefone))
                .comUsuario(usuarioRepository.encontrarUsuarioPeloEmail(CreateUserUtil.email).get())
                .comEndereco(new EnderecoBuilder()
                        .comBairro(bairro)
                        .comCidade(cidade)
                        .comComplemento(complemento)
                        .comEstado(estado)
                        .comNumero(numero)
                        .comRua(rua)
                        .build())
                .build();

        clienteRepository.adicionarCliente(cliente);
    }
}
