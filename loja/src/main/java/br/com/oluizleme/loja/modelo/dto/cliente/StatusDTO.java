package br.com.oluizleme.loja.modelo.dto.cliente;

import br.com.oluizleme.loja.modelo.enums.StatusCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatusDTO {

	private StatusCliente status;
	private Set<StatusCliente> statusAtivos;
	private List<StatusCliente> statusBloqueados;
}
