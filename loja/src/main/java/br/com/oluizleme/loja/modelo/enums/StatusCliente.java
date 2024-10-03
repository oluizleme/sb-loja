package br.com.oluizleme.loja.modelo.enums;

import br.com.oluizleme.loja.modelo.KeyLabelEnum;

public enum StatusCliente implements KeyLabelEnum {

	ATIVO(1, "Ativo"),
	INATIVO(-1, "Inativo"),
	AGUARDANDO_ATIVACAO(2, "Aguardando Ativação"),
	BLOQUEADO(3, "Bloqueado"),
	EXCLUIDO(-2, "Excluído");

	private final Integer key;
	private final String label;

	private StatusCliente(Integer key, String label) {
		this.key = key;
		this.label = label;
	}


	@Override
	public Object getKey() {
		return key;
	}

	@Override
	public String getLabel() {
		return label;
	}

	public static StatusCliente valueOf(Integer key) {
		for (StatusCliente status : StatusCliente.values()) {
			if (status.getKey().equals(key)) {
				return status;
			}
		}
		return null;
	}
}
