package br.com.oluizleme.loja.modelo.dto.produto;

import br.com.oluizleme.loja.modelo.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
public class ProdutoDescricaoDto implements Serializable {

    @Getter
    private Long codigo;
    @Getter
    private String nome;
    @Getter
    private String descricao;
    @Getter
    private BigDecimal valor;

    public ProdutoDescricaoDto(Produto produto) {
        this.codigo = produto.getCodigo();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
    }
}
