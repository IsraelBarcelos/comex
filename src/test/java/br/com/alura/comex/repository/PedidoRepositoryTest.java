package br.com.alura.comex.repository;

import br.com.alura.comex.dominio.pedido.Pedido;
import br.com.alura.comex.infra.categoria.CategoriaRepository;
import br.com.alura.comex.infra.cliente.ClienteRepositoryComJPA;
import br.com.alura.comex.infra.pedido.PedidoRepository;
import br.com.alura.comex.infra.produto.ProdutoRepository;
import br.com.alura.comex.infra.usuario.PerfilRepository;
import br.com.alura.comex.infra.usuario.UsuarioRepository;
import br.com.alura.comex.utils.CreateCategoriaUtil;
import br.com.alura.comex.utils.CreateClienteUtil;
import br.com.alura.comex.utils.CreatePedidoUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepositoryComJPA clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PerfilRepository perfilRepository;

    @BeforeEach
    public void setup() throws Exception {
        CreateClienteUtil.createCliente(clienteRepository, usuarioRepository, passwordEncoder, perfilRepository);
        CreatePedidoUtil.createPedido(clienteRepository, pedidoRepository, usuarioRepository, produtoRepository,
                categoriaRepository, passwordEncoder, perfilRepository);
        CreateCategoriaUtil.createCategoria(categoriaRepository);
    }

    @Test
    public void shouldGetNumbersOfPedidosOfAClientById() {
        Long clientId = clienteRepository.findByNome(CreateClienteUtil.nome).get().getId();
        Integer numberOfPedidos = pedidoRepository.pedidosDeUmCliente(clientId);
        Assertions.assertNotNull(numberOfPedidos);
    }

    @Test
    public void shouldReturnAListOfPedidosOfACategoryByName() {
        String categoryName = CreateCategoriaUtil.nome;
        List<Pedido> pedidos = pedidoRepository.findByItemPedidoProdutoCategoriaNome(categoryName);
        Assertions.assertNotNull(pedidos);
    }

}
