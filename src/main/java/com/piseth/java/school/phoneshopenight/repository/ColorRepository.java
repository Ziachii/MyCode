package com.piseth.java.school.phoneshopenight.repository;

import com.piseth.java.school.phoneshopenight.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

}