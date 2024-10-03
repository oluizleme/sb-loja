package br.com.oluizleme.loja.modelo;

import br.com.oluizleme.loja.modelo.enums.StatusCliente;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    private Long cpf;

    @Getter
    private String nome;

    @Getter
    private String sobrenome;
    @Getter
    @Enumerated(EnumType.STRING)
    private StatusCliente status;


    public Cliente (Long cpf, String nome, String sobrenome, StatusCliente status) {
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.status = status;
    }

}
