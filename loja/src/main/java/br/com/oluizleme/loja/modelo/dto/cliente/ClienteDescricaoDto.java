package br.com.oluizleme.loja.modelo.dto.cliente;

import br.com.oluizleme.loja.modelo.Cliente;
import br.com.oluizleme.loja.modelo.enums.StatusCliente;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class ClienteDescricaoDto implements Serializable {


    @Getter
    private String nome;
    @Getter
    private String sobrenome;
    @Getter
    private Long cpf;
    @Getter
    private StatusCliente status;

    public ClienteDescricaoDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.sobrenome = cliente.getSobrenome();
        this.cpf = cliente.getCpf();
        this.status = cliente.getStatus();
    }

}
