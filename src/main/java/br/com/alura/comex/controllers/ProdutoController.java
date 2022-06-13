package br.com.alura.comex.controllers;

import br.com.alura.comex.dto.ProdutoDTO;
import br.com.alura.comex.models.Produto;
import br.com.alura.comex.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoController {

  @Autowired
  private ProdutoRepository produtoRepository;

  @RequestMapping("/produtos")
  public List<ProdutoDTO> index() {
    List<Produto> produtos = produtoRepository.findAll();
    return ProdutoDTO.converter(produtos);
  }

  @RequestMapping("/produtos/{id}")
  public ProdutoDTO show(Long id) {
    Produto produto = produtoRepository.findById(id).get();
    return new ProdutoDTO(produto);
  }
}
