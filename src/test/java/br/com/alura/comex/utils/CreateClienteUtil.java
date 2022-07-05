package br.com.alura.comex.utils;

import br.com.alura.comex.builders.ClienteBuilder;
import br.com.alura.comex.builders.EnderecoBuilder;
import br.com.alura.comex.models.Cliente;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.UsuarioRepository;

public class CreateClienteUtil {

    public final static String nome = "teste";
    public final static String cpf = "cpf";
    public final static String email = "teste@teste.com";
    public final static String senha = "123456";
    public final static String telefone = "telefone";
    public final static String rua = "rua";
    public final static int numero = 123;
    public final static String complemento = "complemento";
    public final static String bairro = "bairro";
    public final static String cidade = "cidade";
    public final static String estado = "estado";

    public static void createCliente(ClienteRepository clienteRepository, UsuarioRepository usuarioRepository)
            throws Exception {
        if (clienteRepository.findByNome(nome).isPresent()) {
            return;
        }

        CreateUserUtil.createUser(email, senha, usuarioRepository);

        Cliente cliente = new ClienteBuilder()
                .comNome(nome)
                .comCpf(cpf)
                .comTelefone(telefone)
                .comUsuario(usuarioRepository.findByEmail(email).get())
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
