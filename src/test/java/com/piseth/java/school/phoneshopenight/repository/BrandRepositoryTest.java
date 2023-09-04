package com.piseth.java.school.phoneshopenight.repository;

import com.piseth.java.school.phoneshopenight.entity.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@DataJpaTest
public class BrandRepositoryTest {
    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void testFindByNameLike(){
        //given
        Brand brand = new Brand();
        brand.setName("Apple");

        Brand brand2 = new Brand();
        brand2.setName("SAmsung");
        brandRepository.save(brand);
        brandRepository.save(brand2);

        //when
        List<Brand> brands = brandRepository.findByNameLike("%A%");

        //then
        assertEquals(2,brands.size());
        assertEquals("Apple", brands.get(0).getName());
    }

   /* @Test
    public void testFindByNameContaining(){
        Brand brand = new Brand();
        brand.setName("Orange");

        List<Brand> brands = brandRepository.findByNameContaining("%O%");
        assertEquals(1,brands.size());
        assertEquals("Orange",brands.get(0).getName());
    }*/
}
