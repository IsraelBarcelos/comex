package br.com.alura.comex.infra.categoria;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.alura.comex.comercial.dominio.categoria.Categoria;
import br.com.alura.comex.comercial.infra.categoria.CategoriaRepository;
import br.com.alura.comex.comercial.infra.usuario.PerfilRepository;
import br.com.alura.comex.comercial.infra.usuario.UsuarioRepository;
import br.com.alura.comex.utils.CreateCategoriaUtil;
import br.com.alura.comex.utils.CreateSessionUtil;
import br.com.alura.comex.utils.CreateUserUtil;
import br.com.alura.comex.utils.GenerateRandomNumber;

import java.net.URI;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PerfilRepository perfilRepository;

    @BeforeEach
    public void setup() throws Exception {
        CreateUserUtil.createUser(usuarioRepository, passwordEncoder, perfilRepository);
        CreateSessionUtil.createSession(mockMvc, usuarioRepository, passwordEncoder, perfilRepository);
        CreateCategoriaUtil.createCategoria(categoriaRepository);

    }

    @Test
    public void shouldDeleteACategoria() throws Exception {

        Categoria categoria = getCategoriaFromDatabase();

        URI uri = new URI("/api/categorias/" + categoria.getId());

        mockMvc.perform(MockMvcRequestBuilders
                .delete(uri)
                .header("authorization", "Bearer " + CreateSessionUtil.token))
                .andExpect(MockMvcResultMatchers.status().is(204));
    }

    @Test
    public void shouldNotDeleteACategoria() throws Exception {
        Categoria categoria = getCategoriaFromDatabase();

        URI uri = new URI("/api/categorias/" + categoria.getId());

        mockMvc.perform(MockMvcRequestBuilders
                .delete(uri))
                .andExpect(MockMvcResultMatchers.status().is(403));
    }

    private Categoria getCategoriaFromDatabase() throws Exception {

        Optional<Categoria> categoria = categoriaRepository.findByNome(CreateCategoriaUtil.nome);
        if (!categoria.isPresent()) {
            CreateCategoriaUtil.createCategoria(categoriaRepository);
            return categoriaRepository.findByNome(CreateCategoriaUtil.nome).get();
        }
        return categoria.get();

    }

    @Test
    public void shouldReturnAListOfCategoriesWith200Code() throws Exception {

        getCategoriaFromDatabase();

        URI uri = new URI("/api/categorias");

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .header("authorization", "Bearer " + CreateSessionUtil.token))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[*].nome").isNotEmpty());
    }

    @Test
    public void shouldCreateACategoria() throws Exception {
        URI uri = new URI("/api/categorias");
        String json = new JSONObject()
                .put("nome", "testeCategoriaBanco" + GenerateRandomNumber.generateRandomNumber())
                .toString();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .header("authorization", "Bearer " + CreateSessionUtil.token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }
}
