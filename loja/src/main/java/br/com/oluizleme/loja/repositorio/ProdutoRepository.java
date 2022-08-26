package br.com.oluizleme.loja.repositorio;

import br.com.oluizleme.loja.modelo.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
