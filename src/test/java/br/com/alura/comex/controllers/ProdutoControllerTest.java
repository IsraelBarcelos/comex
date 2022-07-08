package br.com.alura.comex.controllers;

import java.math.BigDecimal;
import java.net.URI;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.alura.comex.models.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.PerfilRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import br.com.alura.comex.repository.UsuarioRepository;
import br.com.alura.comex.utils.CreateCategoriaUtil;
import br.com.alura.comex.utils.CreateProdutoUtil;
import br.com.alura.comex.utils.CreateSessionUtil;
import br.com.alura.comex.utils.CreateUserUtil;
import br.com.alura.comex.utils.GenerateRandomNumber;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PerfilRepository perfilRepository;

    @BeforeEach
    public void setup() throws Exception {

        CreateUserUtil.createUser(usuarioRepository, passwordEncoder, perfilRepository);
        CreateSessionUtil.createSession(mockMvc, usuarioRepository, passwordEncoder, perfilRepository);
        CreateProdutoUtil.createProduto(produtoRepository, categoriaRepository);
    }

    @Test
    public void shouldDeleteAProduto() throws Exception {

        Produto produto = getProdutoFromDatabase();

        URI uri = new URI("/api/produtos/" + produto.getId());

        mockMvc.perform(MockMvcRequestBuilders
                .delete(uri)
                .header("authorization", "Bearer " + CreateSessionUtil.token))
                .andExpect(MockMvcResultMatchers.status().is(204));
    }

    private Produto getProdutoFromDatabase() throws Exception {

        return produtoRepository.findByNome(CreateProdutoUtil.nome).get();
    }

    @Test
    public void shouldReturnAListOfProductsWith200Code() throws Exception {
        URI uri = new URI("/api/produtos");

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[*].nome").isNotEmpty());
    }

    @Test
    public void shouldCreateAProduto() throws Exception {

        URI uri = new URI("/api/produtos");
        String json = new JSONObject()
                .put("nome", "testeProdutoBanco" + GenerateRandomNumber.generateRandomNumber())
                .put("categoriaId", categoriaRepository.findByNome(CreateCategoriaUtil.nome).get().getId())
                .put("precoUnitario", BigDecimal.valueOf(564.00))
                .put("quantidadeEstoque", 5)
                .put("descricao", "uma descricao")
                .toString();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .header("authorization", "Bearer " + CreateSessionUtil.token))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

}
