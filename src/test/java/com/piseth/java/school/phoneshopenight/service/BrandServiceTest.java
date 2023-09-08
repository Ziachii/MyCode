package com.piseth.java.school.phoneshopenight.service;

import com.piseth.java.school.phoneshopenight.entity.Brand;
import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.repository.BrandRepository;
import com.piseth.java.school.phoneshopenight.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
    @Mock
    private BrandRepository brandRepository;

    private BrandService brandService;

    @BeforeEach
    public void setUp(){
        brandService = new BrandServiceImpl(brandRepository);
    }

    //todo first method
    /*@Test
    public void testCreate(){
        //given
        Brand brand = new Brand();
        brand.setName("Apple");
        brand.setId(1);

        //when
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);
        Brand brandReturn = brandService.create(new Brand());

        //then
        assertEquals(1, brandReturn.getId());
        assertEquals("Apple",brandReturn.getName());

    }*/
    //todo second method
    @Test
    public void testCreate(){
        //given
        Brand brand =new Brand();
        brand.setName("Apple");

        //when
        brandService.create(brand);
        //then
        verify(brandRepository, times(1)).save(brand);
        //verify(brandRepository, times(1)).delete(brand);
    }

    @Test
    public void testGetByIdSuccess(){
        //given
        Brand brand =new Brand();
        brand.setName("Apple");
        brand.setId(1L);

        //when
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        Brand brandReturn = brandService.getById(1L);

        //then
        assertEquals(1,brandReturn.getId());
        assertEquals("Apple",brandReturn.getName());

    }

    @Test
    public void testGetByIdThrow(){
        //given


        //when
        when(brandRepository.findById(2L)).thenReturn(Optional.empty());
        //brandService.getById(2);
        //todo below is for testing on id that didn't exist in DB
        assertThatThrownBy(()->brandService.getById(2L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Brand With id = 2 not found");
                //.hasMessageEndingWith("not found");

        //then

    }

}
