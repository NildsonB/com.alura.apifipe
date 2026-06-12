package com.alura.apifipe.service;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T converteDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JacksonException e) {
            throw new RuntimeException(e);
        }
    }
}
