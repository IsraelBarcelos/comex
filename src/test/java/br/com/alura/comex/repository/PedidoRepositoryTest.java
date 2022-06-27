package br.com.alura.comex.repository;

import br.com.alura.comex.models.Pedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    public void shouldGetNumbersOfPedidosOfAClientById() {
        Long clientId = 1L;
        Integer numberOfPedidos = pedidoRepository.pedidosDeUmCliente(clientId);
        Assertions.assertNotNull(numberOfPedidos);
        Assertions.assertEquals(numberOfPedidos, 3);
    }

    @Test
    public void shouldReturnAListOfPedidosOfACategoryByName() {
        String categoryName = "teste";
        List<Pedido> pedidos = pedidoRepository.findByItemPedidoProdutoCategoriaNome(categoryName);
        Assertions.assertNotNull(pedidos);
        Assertions.assertEquals(pedidos.size(), 3);
    }

}
