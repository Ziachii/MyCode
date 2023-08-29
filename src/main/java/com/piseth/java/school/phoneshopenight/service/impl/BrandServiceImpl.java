package com.piseth.java.school.phoneshopenight.service.impl;

import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.piseth.java.school.phoneshopenight.entity.Brand;
import com.piseth.java.school.phoneshopenight.repository.BrandRepository;
import com.piseth.java.school.phoneshopenight.service.BrandService;
@Service
public class BrandServiceImpl implements BrandService{
	@Autowired
	private BrandRepository brandRepository;

	@Override
	public Brand create(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public Brand getById(Integer id) {
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
	public Brand update(Integer id, Brand brandUpdate) {
		Brand brand = getById(id);
		brand.setName(brandUpdate.getName());//todo improve update
		return brandRepository.save(brand);
	}

}
