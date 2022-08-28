package br.com.oluizleme.loja.controller;

import br.com.oluizleme.loja.modelo.Produto;
import br.com.oluizleme.loja.modelo.dto.produto.ProdutoDescricaoDto;
import br.com.oluizleme.loja.modelo.dto.produto.ProdutoDto;
import br.com.oluizleme.loja.repositorio.ProdutoRepository;
import br.com.oluizleme.loja.services.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoService produtoService;

    @GetMapping(value = "/")
    public List<ProdutoDto> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return ProdutoDto.converter(produtos);
    }

    @GetMapping(value = "/{produtoCodigo}")
    public ResponseEntity<ProdutoDescricaoDto> consultarProduto(@PathVariable(value = "produtoCodigo") @NotNull Long codigo) {
        Optional<ProdutoDescricaoDto> produtoConsultado = produtoService.consultarProdutoPorCodigo(codigo);
        if(produtoConsultado.isPresent()) {
            return ResponseEntity.ok(produtoConsultado.get());
        }
        return ResponseEntity.notFound().build();
    }
}
