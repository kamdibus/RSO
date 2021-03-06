package com.rso.util;

import com.rso.dto.UserEntityDto;
import com.rso.model.User;
import com.rso.model.UserStatus;
import com.rso.model.UserType;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class DtoHandler {

    public <T, G> G mapEntityToDto(T objectToMap, Class<G> dtoType) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(objectToMap, dtoType);
    }

    public User checkDtoEnumFields(UserEntityDto userDto) {
        User newUser = new User(userDto);
        String typeValue = userDto.getType();
        try {
            UserType typeFromDto = UserType.valueOf(typeValue.toUpperCase());
            newUser.setType(typeFromDto);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return newUser;
    }
}
