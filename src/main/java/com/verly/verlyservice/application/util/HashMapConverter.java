package com.verly.verlyservice.application.util;

import java.io.IOException;
import java.util.Map;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.verly.verlyservice.application.model.customer.Address;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@AllArgsConstructor
public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        String attributeJson = null;
        try {
            attributeJson = objectMapper.writeValueAsString(attribute);
        } catch (final JsonProcessingException e){
            log.error("Erro na escrita do json", e);
        }
        return attributeJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String attributeJson) {
        Map<String, Object> attribute = null;

        try{
            attribute = objectMapper.readValue(attributeJson, Map.class);
        } catch (final IOException e){
            log.error("Erro na leitura do json", e);
        }
        return attribute;
    }
    
}
