package br.com.alura.comex.utils;

import java.net.URI;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jayway.jsonpath.JsonPath;

import br.com.alura.comex.models.Usuario;
import br.com.alura.comex.repository.UsuarioRepository;

public class CreateSessionUtil {

    public static String token;

    public static boolean createSession(MockMvc mockMvc, UsuarioRepository usuarioRepository) throws Exception {

        Optional<Usuario> user = usuarioRepository.findByEmail("teste@teste.com");
        if (!user.isPresent()) {
            CreateUserUtil.createUser("teste@teste.com", "123456", usuarioRepository);
        }

        URI uri = new URI("/auth");
        String json = new JSONObject()
                .put("email", "teste@teste.com")
                .put("senha", "123456")
                .toString();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        token = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.token");
        return mvcResult.getResponse().getStatus() != 200;
    }
}
