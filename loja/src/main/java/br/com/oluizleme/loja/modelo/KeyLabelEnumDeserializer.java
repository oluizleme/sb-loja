package br.com.oluizleme.loja.modelo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import java.io.IOException;
import java.util.*;

public class KeyLabelEnumDeserializer<T extends Enum<T> & KeyLabelEnum> extends JsonDeserializer<Object> implements ContextualDeserializer {

	private Class<T> enumClass;
	private JavaType collectionType;

	public KeyLabelEnumDeserializer() {
		// Construtor vazio para ser utilizado pelo Jackson
	}

	private KeyLabelEnumDeserializer(Class<T> enumClass, JavaType collectionType) {
		this.enumClass = enumClass;
		this.collectionType = collectionType;
	}

	@Override
	public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = p.getCodec().readTree(p);

		if (node.isArray()) {
			// Se o nó for um array, processa como uma coleção (List ou Set)
			Collection<T> enumCollection = createCollectionInstance();
			for (JsonNode arrayItem : node) {
				T enumValue = deserializeEnumNode(arrayItem);
				if (enumValue != null) {
					enumCollection.add(enumValue);
				}
			}
			return enumCollection;
		} else {
			// Se for um único objeto, desserializa para um único valor de enum
			return deserializeEnumNode(node);
		}
	}

	private T deserializeEnumNode(JsonNode node) {
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
		Class<?> rawClass = javaType.getRawClass();

		if (Collection.class.isAssignableFrom(rawClass)) {
			// Trata quando a propriedade é uma coleção (List ou Set)
			JavaType elementType = javaType.getContentType();
			Class<T> enumClass = (Class<T>) elementType.getRawClass();
			return new KeyLabelEnumDeserializer<>(enumClass, javaType);
		} else {
			// Trata quando a propriedade é um único enum
			Class<T> enumClass = (Class<T>) javaType.getRawClass();
			return new KeyLabelEnumDeserializer<>(enumClass, null);
		}
	}

	/**
	 * Cria uma instância de coleção (List ou Set) com base no tipo da propriedade.
	 */
	private Collection<T> createCollectionInstance() {
		if (collectionType != null) {
			Class<?> collectionRawClass = collectionType.getRawClass();
			if (Set.class.isAssignableFrom(collectionRawClass)) {
				return new HashSet<>();
			} else if (List.class.isAssignableFrom(collectionRawClass)) {
				return new ArrayList<>();
			}
		}
		return new ArrayList<>();  // Padrão para List se o tipo não for detectado
	}
}
