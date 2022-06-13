package br.com.alura.comex.controllers;

import br.com.alura.comex.dao.ProdutoDAO;
import br.com.alura.comex.dto.ProdutoDTO;
import br.com.alura.comex.utils.JPAUtil;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoController {
    @RequestMapping("/produtos")
    public List<ProdutoDTO> index() {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produto = new ProdutoDAO(em);
        return ProdutoDTO.converter(produto.listaTodos());
    }
}
