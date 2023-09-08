package com.piseth.java.school.phoneshopenight.service.impl;

import com.piseth.java.school.phoneshopenight.entity.Color;
import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.repository.ColorRepository;
import com.piseth.java.school.phoneshopenight.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;

    @Override
    public Color create(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public Color getById(Long id) {
        return colorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Color",id));

    }
}