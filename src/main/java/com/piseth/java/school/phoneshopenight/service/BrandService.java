package com.piseth.java.school.phoneshopenight.service;

import com.piseth.java.school.phoneshopenight.entity.Brand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Integer id);
	Brand update(Integer id, Brand brandUpdate);

	/*List<Brand> getBrands();*/
	List<Brand> getBrands(String name);
	Page<Brand> getBrands(Map<String, String> params);

}
