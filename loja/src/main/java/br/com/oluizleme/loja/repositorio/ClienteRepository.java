package br.com.oluizleme.loja.repositorio;

import br.com.oluizleme.loja.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public Optional<Cliente> findClienteByCpf(Long cpf);
}
