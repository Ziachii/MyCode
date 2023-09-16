package com.piseth.java.school.phoneshopenight.spec;

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
public class SaleDetailSpec implements Specification<SaleDetail> {
    private  SaleDetailFilter detailFilter;

    @Override
    public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        Join<SaleDetail, Sale> sale = saleDetail.join("sale");
        if(Objects.nonNull(detailFilter.getStartDate())){
            cb.greaterThanOrEqualTo(sale.get("soldDate"), detailFilter.getStartDate());

        }
        if(Objects.nonNull(detailFilter.getStartDate())){
            cb.lessThanOrEqualTo(sale.get("soldDate"), detailFilter.getStartDate());
        }
        Predicate predicate = cb.and(predicates.toArray(Predicate[]::new));
        return predicate;
    }
}
