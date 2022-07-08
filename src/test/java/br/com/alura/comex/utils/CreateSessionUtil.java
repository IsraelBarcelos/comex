package br.com.alura.comex.utils;

import java.net.URI;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jayway.jsonpath.JsonPath;

import br.com.alura.comex.repository.PerfilRepository;
import br.com.alura.comex.repository.UsuarioRepository;

public class CreateSessionUtil {

    public static String token;

    public static void createSession(MockMvc mockMvc, UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder, PerfilRepository perfilRepository) throws Exception {

        CreateUserUtil.createUser(usuarioRepository, passwordEncoder, perfilRepository);

        URI uri = new URI("/auth");
        String json = new JSONObject()
                .put("email", CreateUserUtil.email)
                .put("senha", CreateUserUtil.password)
                .toString();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        token = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.token");

        if (token == null || token.isEmpty() || mvcResult.getResponse().getStatus() != 200) {
            throw new Exception("Token não foi gerado");
        }

    }
}
