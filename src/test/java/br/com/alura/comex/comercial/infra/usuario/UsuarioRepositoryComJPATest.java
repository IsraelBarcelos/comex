package br.com.alura.comex.comercial.infra.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.comex.comercial.dominio.usuario.Usuario;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UsuarioRepositoryComJPATest {

    @Autowired
    UsuarioRepositoryComJPA usuarioRepository;

    @Test
    void shouldReturnAUserByEmail() {
        String email = "teste@teste.com";
        Optional<Usuario> user = usuarioRepository.encontrarUsuarioPeloEmail(email);

        Assertions.assertNotNull(user.get());
        Assertions.assertEquals("teste@teste.com", user.get().getEmail().getEndereco());
    }
}
