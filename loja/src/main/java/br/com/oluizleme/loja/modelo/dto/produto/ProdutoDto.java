package br.com.oluizleme.loja.modelo.dto.produto;

import br.com.oluizleme.loja.modelo.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class ProdutoDto implements Serializable {
    @Getter
    private Long codigo;
    @Getter
    private String nome;

    public ProdutoDto(Produto produto) {
        this.codigo = produto.getCodigo();
        this.nome = produto.getNome();
    }

    public static List<ProdutoDto> converter(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
    }
}
