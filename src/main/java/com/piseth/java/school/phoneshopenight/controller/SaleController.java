package com.piseth.java.school.phoneshopenight.controller;

import com.piseth.java.school.phoneshopenight.Mapper.ProductMapper;
import com.piseth.java.school.phoneshopenight.dto.PriceDTO;
import com.piseth.java.school.phoneshopenight.dto.ProductDTO;
import com.piseth.java.school.phoneshopenight.dto.ProductImportDTO;
import com.piseth.java.school.phoneshopenight.dto.SaleDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.service.ProductService;
import com.piseth.java.school.phoneshopenight.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("sales")
public class SaleController {

	private final SaleService saleService;


	@PostMapping
	public ResponseEntity<?> create(@RequestBody SaleDTO saleDTO) {
		saleService.sell(saleDTO);
		return ResponseEntity.ok().build();
	}
	@PutMapping("{saleId}/cancel")
	public ResponseEntity<?> cancelSale(@PathVariable Long saleId){
		saleService.cancelSale(saleId);
		return ResponseEntity.ok().build();
	}
}