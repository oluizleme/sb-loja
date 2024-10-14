package br.com.oluizleme.loja.modelo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class KeyLabelEnumTypeSerializer extends JsonSerializer<KeyLabelEnum> {
	@Override
	public void serialize(KeyLabelEnum keyLabelEnum, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
		gen.writeStartObject();
		gen.writeFieldName("key");
		gen.writeObject(keyLabelEnum.getKey());
		gen.writeFieldName("label");
		gen.writeString(keyLabelEnum.getLabel());
		gen.writeFieldName("name");
		gen.writeObject(keyLabelEnum.toString());

		gen.writeEndObject();
	}

	public Class<KeyLabelEnum> handledType() {
		return KeyLabelEnum.class;
	}
}
