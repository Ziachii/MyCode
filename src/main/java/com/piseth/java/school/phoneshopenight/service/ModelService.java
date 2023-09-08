package com.piseth.java.school.phoneshopenight.service;

import com.piseth.java.school.phoneshopenight.entity.Model;

import java.util.List;

public interface ModelService {
    Model save(Model model);
    //Model getById(Integer model);

    List<Model> getByBrand(Long brandId);
    Model getById(Long id);
}
