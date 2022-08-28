package br.com.oluizleme.loja.services.cliente;

import br.com.oluizleme.loja.modelo.Cliente;
import br.com.oluizleme.loja.modelo.dto.cliente.ClienteDescricaoDto;
import br.com.oluizleme.loja.repositorio.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@CacheConfig(cacheNames = "cliente")
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Cacheable(value = "clientePorCpf", key = "#cpf", sync = true)
    public Optional<ClienteDescricaoDto> consultarClientePorCpf(Long cpf) {
        log.info("Consultado cliente por cpf {} ", cpf);
        Optional<Cliente> cliente = clienteRepository.findClienteByCpf(cpf);
        if(cliente.isPresent()) {
            return Optional.of(new ClienteDescricaoDto(cliente.get()));
        }
        return Optional.empty();
    }
}
