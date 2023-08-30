package com.piseth.java.school.phoneshopenight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.phoneshopenight.entity.Brand;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> , JpaSpecificationExecutor<Brand> {
    //todo custom method find by name (name can has a lot
    // so well create with list)
    List<Brand> findByNameLike(String name);
    List<Brand> findByNameContaining(String name);
}