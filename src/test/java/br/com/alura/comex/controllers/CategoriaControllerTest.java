package br.com.alura.comex.controllers;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.alura.comex.models.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;

import java.net.URI;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void shouldDeleteACategoria() throws Exception {

        Categoria categoria = getCategoriaFromDatabase();

        URI uri = new URI("/api/categorias/" + categoria.getId());

        mockMvc.perform(MockMvcRequestBuilders
                .delete(uri))
                .andExpect(MockMvcResultMatchers.status().is(204));
    }

    private Categoria getCategoriaFromDatabase() throws Exception {

        Optional<Categoria> categoria = categoriaRepository.findByNome("testeCategoriaBanco");

        if (categoria.isPresent()) {

            return categoria.get();
        }

        this.shouldCreateACategoria();

        return categoriaRepository.findByNome("testeCategoriaBanco").get();

    }

    @Test
    public void shouldReturnAListOfCategoriesWith200Code() throws Exception {
        URI uri = new URI("/api/categorias");

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[*].nome").isNotEmpty());
        ;
    }

    @Test
    public void shouldCreateACategoria() throws Exception {
        URI uri = new URI("/api/categorias");
        String json = new JSONObject()
                .put("nome", "testeCategoriaBanco")
                .toString();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andReturn();
        if (mvcResult.getResponse().getStatus() == 201) {
            return;
        }
        shouldDeleteACategoria();
    }

}
