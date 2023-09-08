package com.piseth.java.school.phoneshopenight.controller;

import com.piseth.java.school.phoneshopenight.Mapper.BrandMapper;
import com.piseth.java.school.phoneshopenight.Mapper.ModelEntityMapper;
import com.piseth.java.school.phoneshopenight.Mapper.ProductMapper;
import com.piseth.java.school.phoneshopenight.dto.*;
import com.piseth.java.school.phoneshopenight.entity.Brand;
import com.piseth.java.school.phoneshopenight.entity.Model;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.service.BrandService;
import com.piseth.java.school.phoneshopenight.service.ModelService;
import com.piseth.java.school.phoneshopenight.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {

	private final ProductService productService;
	private final ProductMapper productMapper;


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {
		//Brand brand = Mapper.toBrand(brandDTO);todo instead of BrandMapper
		Product product = productMapper.toProduct(productDTO);
		product = productService.create(product);
		return ResponseEntity.ok(product);
	}

	@PostMapping("importProduct")
	public ResponseEntity<?> importProduct(@RequestBody ProductImportDTO importDTO){
	 	productService.importProduct(importDTO);
		return ResponseEntity.ok().build();
	}

}