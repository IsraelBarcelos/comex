package br.com.alura.comex.comercial.infra.cliente;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.comex.comercial.dominio.cliente.Cliente;
import br.com.alura.comex.comercial.infra.usuario.PerfilRepository;
import br.com.alura.comex.comercial.infra.usuario.UsuarioRepositoryComJPA;
import br.com.alura.comex.utils.CreateClienteUtil;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ClienteRepositoryComJPATest {

    @Autowired
    private ClienteRepositoryComJPA clienteRepository;

    @Autowired
    private UsuarioRepositoryComJPA usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PerfilRepository perfilRepository;

    @BeforeEach
    public void setup() throws Exception {
        CreateClienteUtil.createCliente(clienteRepository, usuarioRepository, passwordEncoder, perfilRepository);
    }

    @Test
    public void shouldReturnClientByName() {
        Cliente client = clienteRepository.encontrarClientePeloNome(CreateClienteUtil.nome).get();
        Assertions.assertNotNull(client);
        Assertions.assertEquals(CreateClienteUtil.nome, client.getNome());
    }
}
