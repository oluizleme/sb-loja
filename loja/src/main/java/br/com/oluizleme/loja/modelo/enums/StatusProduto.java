package br.com.oluizleme.loja.modelo.enums;

import br.com.oluizleme.loja.modelo.KeyLabelEnum;


public enum StatusProduto implements KeyLabelEnum {

	NOVO(0, "Novo"),
	USADO(-1, "Usado"),
	SEMINOVO(2, "Seminovo");

	private final Integer key;
	private final String label;

	private StatusProduto(Integer key, String label) {
		this.key = key;
		this.label = label;
	}

	@Override
	public Integer getKey() {
		return key;
	}

	@Override
	public String getLabel() {
		return label;
	}

	public static StatusProduto valueOf(Integer key) {
		for (StatusProduto status : StatusProduto.values()) {
			if (status.getKey().equals(key)) {
				return status;
			}
		}
		return null;
	}
}
