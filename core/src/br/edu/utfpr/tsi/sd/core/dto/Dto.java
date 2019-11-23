package br.edu.utfpr.tsi.sd.core.dto;

import br.edu.utfpr.tsi.sd.core.errors.ParseDtoException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public interface Dto {
    ObjectMapper objectMapper = new ObjectMapper();

    default String toJsonString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new ParseDtoException("Error while converting Dto to JSON", e);
        }
    }

    static <D extends Dto> D fromJsonString(String json, Class<D> dtoTypeClass) {
        try {
            return objectMapper.readValue(json, dtoTypeClass);
        } catch (IOException e) {
            throw new ParseDtoException("Error while creating Dto from JSON", e);
        }
    }
}
