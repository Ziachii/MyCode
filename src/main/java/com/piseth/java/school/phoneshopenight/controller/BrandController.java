package com.piseth.java.school.phoneshopenight.controller;

import com.piseth.java.school.phoneshopenight.Mapper.BrandMapper;
import com.piseth.java.school.phoneshopenight.Mapper.ModelEntityMapper;
import com.piseth.java.school.phoneshopenight.dto.ModelDTO;
import com.piseth.java.school.phoneshopenight.dto.PageDTO;
import com.piseth.java.school.phoneshopenight.entity.Model;
import com.piseth.java.school.phoneshopenight.service.ModelService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RestController
@RequestMapping("brands")
public class BrandController {

	private final BrandService brandService;

	private final ModelService modelService;
	private final ModelEntityMapper modelMapper;
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
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Long brandId){
		Brand brand = brandService.getById(brandId);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
	}

	//todo===============UPDATE=============================
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long brandId, @RequestBody BrandDTO brandDTO){
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
	//todo list of models in a brand
	@GetMapping("{id}/models" )
	public ResponseEntity<?> getModelByBrand(@PathVariable("id") Long brandId){
		List<Model> brands = modelService.getByBrand(brandId);
		List<ModelDTO> list = brands.stream()
				.map(modelMapper::toModelDTO)
				.toList();
		return ResponseEntity.ok(list);

	}
}