package com.piseth.java.school.phoneshopenight.service;

import com.piseth.java.school.phoneshopenight.entity.Brand;
import com.piseth.java.school.phoneshopenight.entity.Color;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ColorService {
	Color create(Color color);
	Color getById(Long id);
}
