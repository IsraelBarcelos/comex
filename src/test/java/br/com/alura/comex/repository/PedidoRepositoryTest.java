package br.com.alura.comex.repository;

import br.com.alura.comex.models.Pedido;
import br.com.alura.comex.utils.CreateCategoriaUtil;
import br.com.alura.comex.utils.CreateClienteUtil;
import br.com.alura.comex.utils.CreatePedidoUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @BeforeEach
    public void setup() throws Exception {
        CreateClienteUtil.createCliente(clienteRepository, usuarioRepository);
        CreatePedidoUtil.createPedido(clienteRepository, pedidoRepository, usuarioRepository, produtoRepository,
                categoriaRepository);
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
