package br.com.oluizleme.loja.modelo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import java.io.IOException;

public class KeyLabelEnumDeserializer<T extends Enum<T> & KeyLabelEnum> extends JsonDeserializer<T> implements ContextualDeserializer {

	private Class<T> enumClass;

	public KeyLabelEnumDeserializer() {
		// Construtor vazio para ser utilizado pelo Jackson
	}

	private KeyLabelEnumDeserializer(Class<T> enumClass) {
		this.enumClass = enumClass;
	}

	@Override
	public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = p.getCodec().readTree(p);
		if (node.has("key")) {
			int key = node.get("key").asInt();
			for (T enumConstant : enumClass.getEnumConstants()) {
				if (enumConstant.getKey().equals(key)) {
					return enumConstant;
				}
			}
		}
		return null;
	}

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
		JavaType javaType = property.getType();
		Class<T> enumClass = (Class<T>) javaType.getRawClass();
		return new KeyLabelEnumDeserializer<>(enumClass);
	}

}