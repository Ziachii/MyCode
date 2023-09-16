package com.piseth.java.school.phoneshopenight.spec;

import com.piseth.java.school.phoneshopenight.entity.ProductImportHistory;
import com.piseth.java.school.phoneshopenight.entity.Sale;
import com.piseth.java.school.phoneshopenight.entity.SaleDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class ProductImportHistoriesSpec implements Specification<ProductImportHistory> {
    private  ProductImportHistoriesFilter importFilter;

    @Override
    public Predicate toPredicate(Root<ProductImportHistory> importHistory, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        //Join<SaleDetail, Sale> sale = saleDetail.join("sale");
        if(Objects.nonNull(importFilter.getStartDate())){
            cb.greaterThanOrEqualTo(importHistory.get("dateImport"), importFilter.getStartDate());

        }
        if(Objects.nonNull(importFilter.getStartDate())){
            cb.lessThanOrEqualTo(importHistory.get("dateImport"), importFilter.getStartDate());
        }
        Predicate predicate = cb.and(predicates.toArray(Predicate[]::new));
        return predicate;
    }

}
