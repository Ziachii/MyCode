package com.piseth.java.school.phoneshopenight.service.impl;

import com.piseth.java.school.phoneshopenight.entity.Model;
import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.repository.ModelRepository;
import com.piseth.java.school.phoneshopenight.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepository modelRepository;
    @Override
    public Model save(Model model) {
        //Integer brandId = model.getBrand().getId();
        //brandService.getById(brandId);
        //Model model = modelMapper.toModel(modelDTO);
        return modelRepository.save(model);

    }

    @Override
    public List<Model> getByBrand(Long brandId) {
        return modelRepository.findByBrandId(brandId);
    }

    @Override
    public Model getById(Long id) {
        return modelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Model",id));

    }

}
