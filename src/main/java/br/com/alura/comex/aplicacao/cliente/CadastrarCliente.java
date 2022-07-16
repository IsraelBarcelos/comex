package br.com.alura.comex.aplicacao.cliente;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alura.comex.dominio.PublicadorDeEventos;
import br.com.alura.comex.dominio.cliente.Cliente;
import br.com.alura.comex.dominio.cliente.ClienteCadastrado;
import br.com.alura.comex.dominio.cliente.Cpf;
import br.com.alura.comex.dominio.cliente.RepositorioDeCliente;
import br.com.alura.comex.infra.usuario.UsuarioRepository;

public class CadastrarCliente {

    private final RepositorioDeCliente clienteRepository;
    private final PublicadorDeEventos publicadorDeEventos;

    @Autowired
    UsuarioRepository usuarioRepository;

    public CadastrarCliente(RepositorioDeCliente clienteRepository, Cpf cpfDoAluno,
            PublicadorDeEventos publicadorDeEventos) {
        this.clienteRepository = clienteRepository;
        this.publicadorDeEventos = publicadorDeEventos;

    }

    public void executa(ClienteForm form) {
        Cliente cliente = form.converter(usuarioRepository);
        clienteRepository.adicionarCliente(cliente);
        ClienteCadastrado clienteCadastrado = new ClienteCadastrado(cliente.getCpf(), LocalDateTime.now());
        publicadorDeEventos.publicar(clienteCadastrado);
    }

}
