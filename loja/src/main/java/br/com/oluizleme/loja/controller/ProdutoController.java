package br.com.oluizleme.loja.controller;

import br.com.oluizleme.loja.modelo.Produto;
import br.com.oluizleme.loja.repositorio.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/")
    public List<Produto> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos;
    }
}
