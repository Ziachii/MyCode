package com.piseth.java.school.phoneshopenight.service;

import com.piseth.java.school.phoneshopenight.dto.ProductImportDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Map;

public interface ProductService {
    Product create(Product product);
    Product getById (Long id);
    Product getByModelIdAndColorId (Long modelId, Long colorId);

    void importProduct(ProductImportDTO importDTO); //save from table

    void setSalePrice(Long productId, BigDecimal price);

    void validateStock(Long productId, Integer numberOfUnit);

    Map<Integer, String> uploadProduct(MultipartFile file);
}
