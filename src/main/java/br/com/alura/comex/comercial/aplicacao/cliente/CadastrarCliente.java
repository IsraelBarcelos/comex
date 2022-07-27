package br.com.alura.comex.comercial.aplicacao.cliente;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alura.comex.comercial.dominio.cliente.Cliente;
import br.com.alura.comex.comercial.dominio.cliente.ClienteCadastrado;
import br.com.alura.comex.comercial.dominio.cliente.RepositorioDeCliente;
import br.com.alura.comex.comercial.infra.usuario.UsuarioRepositoryComJPA;
import br.com.alura.comex.shared.dominio.PublicadorDeEventos;

public class CadastrarCliente {

    private final RepositorioDeCliente clienteRepository;
    private final PublicadorDeEventos publicadorDeEventos;

    @Autowired
    UsuarioRepositoryComJPA usuarioRepository;

    public CadastrarCliente(RepositorioDeCliente clienteRepository,
            PublicadorDeEventos publicadorDeEventos) {
        this.clienteRepository = clienteRepository;
        this.publicadorDeEventos = publicadorDeEventos;

    }

    public void executa(ClienteForm form) {
        Cliente cliente = form.converter(usuarioRepository);
        clienteRepository.adicionarCliente(cliente);
        ClienteCadastrado clienteCadastrado = new ClienteCadastrado(cliente.getCpf());
        publicadorDeEventos.publicar(clienteCadastrado);
    }

}
