package com.example.gradinfo.mapper;

import org.modelmapper.ModelMapper;


public class CommonMapper {
    public static <T,K> T convertToDto(K entity, Class<T> object) {
        ModelMapper modelMapper = new ModelMapper();

        T res = modelMapper.map(entity, object);

        return res;
    }


}
