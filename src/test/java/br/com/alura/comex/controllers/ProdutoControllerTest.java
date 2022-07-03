package br.com.alura.comex.controllers;

import java.net.URI;
import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.alura.comex.models.Produto;
import br.com.alura.comex.repository.ProdutoRepository;

@WebMvcTest
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    public void shouldDeleteAProduto() throws Exception {

        Produto produto = getProdutoFromDatabase();

        URI uri = new URI("/api/produtos/" + produto.getId());

        mockMvc.perform(MockMvcRequestBuilders
                .delete(uri))
                .andExpect(MockMvcResultMatchers.status().is(204));
    }

    private Produto getProdutoFromDatabase() throws Exception {

        Optional<Produto> Produto = produtoRepository.findByNome("testeProdutoBanco");

        if (Produto.isPresent()) {

            return Produto.get();
        }

        this.shouldCreateAProduto();

        return produtoRepository.findByNome("testeProdutoBanco").get();

    }

    @Test
    public void shouldReturnAListOfProductsWith200Code() throws Exception {
        URI uri = new URI("/api/produtos");

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[*].nome").isNotEmpty());
        ;
    }

    @Test
    public void shouldCreateAProduto() throws Exception {
        URI uri = new URI("/api/produtos");
        String json = new JSONObject()
                .put("nome", "testeProdutoBanco")
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
        shouldDeleteAProduto();
    }

}
