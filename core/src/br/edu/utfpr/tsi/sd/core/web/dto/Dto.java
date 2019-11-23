package br.edu.utfpr.tsi.sd.core.web.dto;

import br.edu.utfpr.tsi.sd.core.errors.ParseDtoException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.io.IOException;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;
import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;
import static com.fasterxml.jackson.annotation.PropertyAccessor.*;

public interface Dto {
    default String toJsonString() {
        var objectMapper = new ObjectMapper();

        objectMapper.registerModule(new ParameterNamesModule(PROPERTIES));
        objectMapper.setVisibility(FIELD, ANY);

        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new ParseDtoException("Error while converting Dto to JSON", e);
        }
    }

    static <D extends Dto> D fromJsonString(String json, Class<D> dtoTypeClass) {
        var objectMapper = new ObjectMapper();

        objectMapper.registerModule(new ParameterNamesModule(PROPERTIES));
        objectMapper.setVisibility(FIELD, ANY);

        try {
            return objectMapper.readValue(json, dtoTypeClass);
        } catch (IOException e) {
            throw new ParseDtoException("Error while creating Dto from JSON", e);
        }
    }
}
