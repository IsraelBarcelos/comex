package br.com.alura.services.processadores.component;
// package br.com.alura.services;
// import java.util.List;
// import java.util.Map;
// public class MontadorDePedidos {
//   List<Map<String, String>> lines;
//   public MontadorDePedidos(List<Map<String, String>> lines) {
//     this.lines = lines;
//   }
//   public montar() {
//     lines.forEach(
//       colsInLine -> {
//         LocalDate data = LocalDate.parse(
//           colsInLine.get("DATA"),
//           DateTimeFormatter.ofPattern("dd/MM/yyyy")
//         );
//         String nomeDoCliente = colsInLine.get("CLIENTE");
//         this.clientePlaceholder =
//           this.clientesDoSistema.adicionaNovoClienteOuRejeita(nomeDoCliente);
//         Pedido pedido = new Pedido(
//           new Produto(
//             colsInLine.get("PRODUTO"),
//             colsInLine.get("CATEGORIA"),
//             new BigDecimal(colsInLine.get("PRECO"))
//           ),
//           clientePlaceholder,
//           Integer.parseInt(colsInLine.get("QUANTIDADE")),
//           data
//         );
//         pedidos.add(pedido);
//       }
//     );
//   }
// }
