package com.piseth.java.school.phoneshopenight.service.impl;

import com.piseth.java.school.phoneshopenight.dto.ProductSoldDTO;
import com.piseth.java.school.phoneshopenight.dto.SaleDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.Sale;
import com.piseth.java.school.phoneshopenight.entity.SaleDetail;
import com.piseth.java.school.phoneshopenight.exception.ApiException;
import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.repository.ProductRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleDetailRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleRepository;
import com.piseth.java.school.phoneshopenight.service.ProductService;
import com.piseth.java.school.phoneshopenight.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SaleServiceImpl implements SaleService {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    @Override
    public void sell(SaleDTO saleDTO) {
        //validation
        //validate(saleDTO);
        List<Long> productIds = saleDTO.getProducts().stream()
                .map(ProductSoldDTO::getProductId).toList();
        //validate product
        productIds.forEach(productService::getById);


        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        //validate stock
        saleDTO.getProducts()
                .forEach(ps ->{
                    Product product = productMap.get(ps.getProductId());
                    if(product.getAvailableUnit() < ps.getNumberOfUnit()){
                        throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is  not enough product in stock".formatted(product.getName()));
                    }
                });
        //sale
        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSaleDate());
        saleRepository.save(sale);
        //sale detail
        saleDTO.getProducts().forEach(ps ->{
            Product product = productMap.get(ps.getProductId());
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setAmount(product.getSalePrice());
            saleDetail.setProduct(product);
            saleDetail.setSale(sale);
            saleDetail.setUnit(ps.getNumberOfUnit());
            saleDetailRepository.save(saleDetail);
        //cut stock
            Integer availableUnit = product.getAvailableUnit() - ps.getNumberOfUnit();
            product.setAvailableUnit(availableUnit);
            productRepository.save(product);
        });
    }

    @Override
    public Sale getById(Long saleId) {
        return saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("sale", saleId));
    }

    /*private void validate2(SaleDTO saleDTO){
        List<Long> productIds = saleDTO.getProducts().stream()
                        .map(ProductSoldDTO::getProductId).toList();
        //validate product
        productIds.forEach(productService::getById);
                //.forEach(productId ->productService.getById(productId));

        Map<Long, Product> productMap = productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        //validate stock
        saleDTO.getProducts()
                .forEach(ps ->{
                    Product product = productMap.get(ps.getProductId());
                    if(product.getAvailableUnit() < ps.getNumberOfUnit()){
                        throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is  not enough product in stock".formatted(product.getName()));
                    }
                });
    }*/
    //todo this method validation
    private void validate(SaleDTO saleDTO){
        saleDTO.getProducts().forEach(ps ->{
            Product product = productService.getById(ps.getProductId());
            if(product.getAvailableUnit() < ps.getNumberOfUnit()){
                throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is  not enough product in stock".formatted(product.getName()));
            }
        });
    }

    private void saveSale(SaleDTO saleDTO){
        Sale sale = new Sale();
       sale.setSoldDate(saleDTO.getSaleDate());
        saleRepository.save(sale);

       //sale detail
        saleDTO.getProducts().forEach((ps ->{
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setAmount(null);
        }));
    }

    @Override
    public void cancelSale(Long saleId) {
        //update sale status
        Sale sale = getById(saleId);
        sale.setActive(false);
        saleRepository.save(sale);
        // update stock
        List<SaleDetail> saleDatails = saleDetailRepository.findBySaleId(saleId);

        List<Long> productIds = saleDatails.stream()
                .map(sd -> sd.getProduct().getId())
                .toList();

        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream()
                        .collect(Collectors.toMap(Product::getId, Function.identity()));

        saleDatails.forEach(sd ->{
            Product product = productMap.get(sd.getProduct().getId());
            product.setAvailableUnit(product.getAvailableUnit() + sd.getUnit());
            productRepository.save(product);

        });
    }

}
