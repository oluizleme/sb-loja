package br.com.oluizleme.loja.modelo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = KeyLabelEnumTypeSerializer.class)
@JsonDeserialize(using = KeyLabelEnumDeserializer.class)
public interface KeyLabelEnum {
	Object getKey();
	String getLabel();

}
