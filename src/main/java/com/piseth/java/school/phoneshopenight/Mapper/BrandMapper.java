package com.piseth.java.school.phoneshopenight.Mapper;

import com.piseth.java.school.phoneshopenight.dto.BrandDTO;
import com.piseth.java.school.phoneshopenight.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    //todo dto to brand(Entities)
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
    Brand toBrand(BrandDTO dto);
    //todo brand to dto

    BrandDTO toBrandDTO(Brand entity);

}