package br.com.oluizleme.loja.services.produto;

import br.com.oluizleme.loja.modelo.Produto;
import br.com.oluizleme.loja.modelo.dto.produto.ProdutoDescricaoDto;
import br.com.oluizleme.loja.repositorio.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Cacheable(value = "produtoPorCodigo", key = "#codigo", sync = true)
    public Optional<ProdutoDescricaoDto> consultarProdutoPorCodigo(Long codigo) {
        log.info("Consultado produto por código {} ", codigo);
        Optional<Produto> produto = produtoRepository.findById(codigo);
        if(produto.isPresent()) {
            return Optional.of(new ProdutoDescricaoDto(produto.get()));
        }
        return Optional.empty();
    }
}
