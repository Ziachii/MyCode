package com.piseth.java.school.phoneshopenight.controller;

import com.piseth.java.school.phoneshopenight.Mapper.BrandMapper;
import com.piseth.java.school.phoneshopenight.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.piseth.java.school.phoneshopenight.dto.BrandDTO;
import com.piseth.java.school.phoneshopenight.entity.Brand;
import com.piseth.java.school.phoneshopenight.service.BrandService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("brands")
public class BrandController {
	
	@Autowired
	private BrandService brandService;
    //todo ============CREATE==========================
	//todo first way using brandDTO in postman
	/*@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
		Brand brand = Mapper.toBrand(brandDTO);
		brand = brandService.create(brand);
		return ResponseEntity.ok(Mapper.toBrandDTO(brand));
	}*/
	//todo ============CREATE===========================
	//todo second way using brand in postman
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
		//Brand brand = Mapper.toBrand(brandDTO);todo instead of BrandMapper
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand = brandService.create(brand);
		return ResponseEntity.ok(brand);
	}

	//todo===============GET BY ID==========================
	@GetMapping("{id}" )
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Integer brandId){
		Brand brand = brandService.getById(brandId);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
	}

	//todo===============UPDATE=============================
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer brandId, @RequestBody BrandDTO brandDTO){
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		Brand updatedBrand = brandService.update(brandId, brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(updatedBrand));
	}
	//todo ===================GET LIST NAME(DTO)=============
/*	@GetMapping
	public ResponseEntity<?> getBrands(){
		List<BrandDTO> list = brandService.getBrands()
				.stream()
				.map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
				.collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}*/

	//TODO==================GET BY NAME=====================
	/*@GetMapping("filter")
	public ResponseEntity<?> getBrands(@RequestParam("name") String name){
		List<BrandDTO> list = brandService.getBrands(name)
				.stream()
				.map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
				.collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}*/
	//todo ================entity have many params(have to use map)=====================
	@GetMapping
	public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params){
		Page<Brand> page = brandService.getBrands(params);
		PageDTO pageDTO = new PageDTO(page);
		/*List<BrandDTO> list = brandService.getBrands(params)
				.stream()
				.map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
				.collect(Collectors.toList());*/

		return ResponseEntity.ok(pageDTO);
	}
}
