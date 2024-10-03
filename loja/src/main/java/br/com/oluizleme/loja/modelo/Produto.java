package br.com.oluizleme.loja.modelo;

import br.com.oluizleme.loja.modelo.enums.StatusProduto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long codigo;
    @Getter
    private String nome;
    @Getter
    private String descricao;
    @Getter
    private BigDecimal valor;
    @Getter
    @Enumerated(EnumType.STRING)
    private StatusProduto status;

    public Produto(String nome, String descricao, BigDecimal valor, StatusProduto status) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
    }
}
