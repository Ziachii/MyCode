package com.piseth.java.school.phoneshopenight.Mapper;

import com.piseth.java.school.phoneshopenight.dto.ProductDTO;
import com.piseth.java.school.phoneshopenight.dto.ProductImportDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.ProductImportHistory;
import com.piseth.java.school.phoneshopenight.service.ColorService;
import com.piseth.java.school.phoneshopenight.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring",
        uses = {ModelService.class, ColorService.class})
public interface ProductMapper {

    @Mapping(target = "model", source = "modelId")
    @Mapping(target = "color", source = "colorId")
    Product toProduct(ProductDTO productDTO);

    //todo import product
    @Mapping(target = "dateImport" , source = "importDTO.importDate")
    @Mapping(target = "pricePerUnit", source = "importDTO.importPrice")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "id", ignore = true)
    ProductImportHistory toProductImportHistory(ProductImportDTO importDTO, Product product);
}
