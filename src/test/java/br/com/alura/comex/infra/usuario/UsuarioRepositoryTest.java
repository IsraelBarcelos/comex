package br.com.alura.comex.infra.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.comex.comercial.dominio.usuario.Usuario;
import br.com.alura.comex.comercial.infra.usuario.UsuarioRepository;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void shouldReturnAUserByEmail() {
        String email = "teste@teste.com";
        Optional<Usuario> user = usuarioRepository.findByEmail(email);

        Assertions.assertNotNull(user.get());
        Assertions.assertEquals(user.get().getEmail(), "teste@teste.com");
    }
}
