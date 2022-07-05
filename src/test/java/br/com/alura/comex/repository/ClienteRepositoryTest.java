package br.com.alura.comex.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.comex.models.Cliente;
import br.com.alura.comex.utils.CreateClienteUtil;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ClienteRepositoryTest {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setup() throws Exception {
        CreateClienteUtil.createCliente(clienteRepository, usuarioRepository);

    }

    @Test
    public void shouldReturnClientByName() {
        Cliente client = clienteRepository.findByNome("teste").get();
        Assertions.assertNotNull(client);
        Assertions.assertEquals(client.getNome(), "teste");
    }
}
