package br.com.alura.comex.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.alura.comex.builders.EnderecoBuilder;
import br.com.alura.comex.cliente.Cliente;
import br.com.alura.comex.cliente.ClienteBuilder;
import br.com.alura.comex.cliente.ClienteRepository;
import br.com.alura.comex.cliente.Telefone;
import br.com.alura.comex.repository.PerfilRepository;
import br.com.alura.comex.repository.UsuarioRepository;

public class CreateClienteUtil {

    public final static String nome = "teste";
    public final static String cpf = "cpf";
    public final static String telefone = "telefone";
    public final static String rua = "rua";
    public final static int numero = 123;
    public final static String complemento = "complemento";
    public final static String bairro = "bairro";
    public final static String cidade = "cidade";
    public final static String estado = "estado";
    public final static Integer ddd = 54;
    public final static Integer numeroTelefone = 123456789;

    public static void createCliente(ClienteRepository clienteRepository, UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder, PerfilRepository perfilRepository)
            throws Exception {
        if (clienteRepository.findByNome(nome).isPresent()) {
            return;
        }

        CreateUserUtil.createUser(usuarioRepository, passwordEncoder, perfilRepository);

        Cliente cliente = new ClienteBuilder()
                .comNome(nome)
                .comCpf(cpf)
                .comTelefone(new Telefone(ddd, numeroTelefone))
                .comUsuario(usuarioRepository.findByEmail(CreateUserUtil.email).get())
                .comEndereco(new EnderecoBuilder()
                        .comBairro(bairro)
                        .comCidade(cidade)
                        .comComplemento(complemento)
                        .comEstado(estado)
                        .comNumero(numero)
                        .comRua(rua)
                        .build())
                .build();

        clienteRepository.save(cliente);
    }
}
