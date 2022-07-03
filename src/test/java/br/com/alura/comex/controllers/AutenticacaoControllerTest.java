package br.com.alura.comex.controllers;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.alura.comex.models.Usuario;
import br.com.alura.comex.repository.UsuarioRepository;

import java.net.URI;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AutenticacaoControllerTest {

        @Autowired
        private MockMvc mockMvc;

        Usuario usuarioTeste;

        @Autowired
        private UsuarioRepository usuarioRepository;

        @BeforeEach
        public void setUsuario() throws Exception {
                usuarioTeste = getUsuarioFromDatabase();
        }

        private Usuario getUsuarioFromDatabase() throws Exception {

                Optional<Usuario> usuario = usuarioRepository.findByEmail("teste@teste.com");

                if (usuario.isPresent()) {

                        return usuario.get();
                }

                this.createUser();

                return usuarioRepository.findByEmail("teste@teste.com").get();

        }

        private Usuario createUser() throws Exception {
                Usuario usuario = new Usuario();
                usuario.setNome("testeCategoriaBanco");
                usuario.setEmail("teste@teste.com");
                usuario.setSenha("123456");
                usuarioRepository.save(usuario);
                return usuario;
        }

        @Test
        public void shouldReturn400HttpErrorIfUserDataIsWrong() throws Exception {
                URI uri = new URI("/auth");
                String json = new JSONObject()
                                .put("email", "teste@1234.email.com")
                                .put("senha", "123456")
                                .toString();

                mockMvc.perform(MockMvcRequestBuilders
                                .post(uri)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().is(400));
        }

        @Test
        public void shouldReturn200IfAllIsRight() throws Exception {
                URI uri = new URI("/auth");
                String json = new JSONObject()
                                .put("email", "teste@teste.com")
                                .put("senha", "123456")
                                .toString();

                mockMvc.perform(MockMvcRequestBuilders
                                .post(uri)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().is(200));
        }

        @Test
        public void shouldReturn400HttpErrorForUserDataIsIncomplete() throws Exception {
                URI uri = new URI("/auth");
                String json = new JSONObject()
                                .put("email", "teste@1234.email.com")
                                .toString();

                mockMvc.perform(MockMvcRequestBuilders
                                .post(uri)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().is(400));
        }

}
