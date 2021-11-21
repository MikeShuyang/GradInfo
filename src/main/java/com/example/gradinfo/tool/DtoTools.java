package com.example.gradinfo.tool;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


public class DtoTools {
    public static <T,K> T convertToDto(K entity, Class<T> object) {
        ModelMapper modelMapper = new ModelMapper();

        T res = modelMapper.map(entity, object);

        return res;
    }


}
