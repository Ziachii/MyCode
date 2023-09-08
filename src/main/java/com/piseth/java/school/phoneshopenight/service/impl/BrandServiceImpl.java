package com.piseth.java.school.phoneshopenight.service.impl;

import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.service.util.PageUtil;
import com.piseth.java.school.phoneshopenight.spec.BrandFilter;
import com.piseth.java.school.phoneshopenight.spec.BrandSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.piseth.java.school.phoneshopenight.entity.Brand;
import com.piseth.java.school.phoneshopenight.repository.BrandRepository;
import com.piseth.java.school.phoneshopenight.service.BrandService;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{
	@Autowired
	private final BrandRepository brandRepository;

	@Override
	public Brand create(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public Brand getById(Long id) {
		/*Optional<Brand> brandOptional = brandRepository.findById(id);

		if(brandOptional.isPresent()){
			return brandOptional.get();
		}
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Brand with id = "+id +"not found");
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Brand with id = %d not found".formatted(id));
		*/

		return brandRepository.findById(id)
				//.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Brand with id = %d not found".formatted(id)));
				.orElseThrow(() -> new ResourceNotFoundException("Brand",id));
	}

	@Override
	public Brand update(Long id, Brand brandUpdate) {
		Brand brand = getById(id);
		brand.setName(brandUpdate.getName());//todo improve update
		return brandRepository.save(brand);
	}

	/*@Override
	public List<Brand> getBrands() {
		return brandRepository.findAll();
	}*/

	@Override
	public List<Brand> getBrands(String name) {
		//return brandRepository.findByNameContaining("%" + name+"%");
		return brandRepository.findByNameContaining(name);
	}
	/*@Override
	public List<Brand> getBrands(Map<String, String> params) {
		BrandFilter brandFilter = new BrandFilter();

		if(params.containsKey("name")){
			String name = params.get("name");
			brandFilter.setName(name);
		}
		if(params.containsKey("id")){
			String id = params.get("id");
			brandFilter.setId(Integer.parseInt(id));
		}
		*//*int pageLimit = 1;
		if(params.containsKey(PageUtil.PAGE_LIMIT)){
			pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));

		}
		int pageNumber = 1;
		if(params.containsKey(PageUtil.PAGE_NUMBER)){
			pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
		}*//*
		BrandSpec brandSpec = new BrandSpec(brandFilter);

		//Pageable pageable = PageUtil.getPageable(pageNumber,pageLimit);

		return brandRepository.findAll(brandSpec);

	}*/
	@Override
	public Page<Brand> getBrands(Map<String, String> params) {
		BrandFilter brandFilter = new BrandFilter();

		if(params.containsKey("name")){
			String name = params.get("name");
			brandFilter.setName(name);
		}
		if(params.containsKey("id")){
			String id = params.get("id");
			brandFilter.setId(Integer.parseInt(id));
		}
		//todo add to function for pageable
		int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
		if(params.containsKey(PageUtil.PAGE_LIMIT)){
			pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));

		}
		int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
		if(params.containsKey(PageUtil.PAGE_NUMBER)){
			pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
		}
		BrandSpec brandSpec = new BrandSpec(brandFilter);

		Pageable pageable = PageUtil.getPageable(pageNumber,pageLimit);

		Page<Brand> page = brandRepository.findAll(brandSpec, pageable);
		return page;
	}

}