package com.rso.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DtoHandler {

    public <T, G> G mapEntityToDto(T objectToMap, Class<G> dtoType) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(objectToMap, dtoType);
    }
}