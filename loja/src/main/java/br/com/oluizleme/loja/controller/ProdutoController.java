package br.com.oluizleme.loja.controller;

import br.com.oluizleme.loja.modelo.Produto;
import br.com.oluizleme.loja.modelo.dto.ProdutoDescricaoDto;
import br.com.oluizleme.loja.modelo.dto.ProdutoDto;
import br.com.oluizleme.loja.repositorio.ProdutoRepository;
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

    @GetMapping("/")
    public List<ProdutoDto> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return ProdutoDto.converter(produtos);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ProdutoDescricaoDto> consultarProduto(@PathVariable @NotNull Long codigo) {
        Optional<Produto> optional = produtoRepository.findById(codigo);
        if(optional.isPresent()) {
            return ResponseEntity.ok(new ProdutoDescricaoDto(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
