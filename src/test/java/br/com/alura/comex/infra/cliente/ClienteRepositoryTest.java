package br.com.alura.comex.infra.cliente;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.comex.dominio.cliente.Cliente;
import br.com.alura.comex.infra.usuario.PerfilRepository;
import br.com.alura.comex.infra.usuario.UsuarioRepository;
import br.com.alura.comex.utils.CreateClienteUtil;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepositoryComJPA clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

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
