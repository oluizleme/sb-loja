package br.com.oluizleme.loja.controller;

import br.com.oluizleme.loja.modelo.Cliente;
import br.com.oluizleme.loja.modelo.dto.cliente.ClienteDescricaoDto;
import br.com.oluizleme.loja.modelo.dto.cliente.StatusDTO;
import br.com.oluizleme.loja.modelo.enums.StatusCliente;
import br.com.oluizleme.loja.repositorio.ClienteRepository;
import br.com.oluizleme.loja.services.cliente.ClienteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/")
    public List<Cliente> listarClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    @GetMapping(value = "/{cpf}")
    public ResponseEntity<ClienteDescricaoDto> consultarClientePorCpf(@PathVariable(value = "cpf") @NotNull Long cpf){
        Optional<ClienteDescricaoDto> cliente = clienteService.consultarClientePorCpf(cpf);
        if(cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/status")
    public ResponseEntity<StatusDTO> consultarStatusClientes() {
        StatusDTO statusDTO = clienteService.consultarStatusClientes();
        return ResponseEntity.ok(statusDTO);

    }
}
