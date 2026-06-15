package com.alura.apifipe.service;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.type.CollectionType;

import java.util.List;

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

    @Override
    public <T> List<T> converteLista(String json, Class<T> classe) {
        CollectionType lista = mapper.getTypeFactory().constructCollectionType(List.class, classe);
        try {
            return mapper.readValue(json, lista);
        } catch (JacksonException e) {
            throw new RuntimeException(e);
        }
    }


}
