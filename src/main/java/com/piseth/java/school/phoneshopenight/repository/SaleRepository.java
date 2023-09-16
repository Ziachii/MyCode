package com.piseth.java.school.phoneshopenight.repository;

import com.piseth.java.school.phoneshopenight.entity.Sale;
import com.piseth.java.school.phoneshopenight.projection.ProductSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(value = "select p.id as productId, p.name productName, sum(sd.unit) unit, sum(sd.unit * sd.sold_amount)totalAmount \n" +
            "from sale_details sd\n" +
            "inner join sales s on sd.sale_id = s.sale_id \n" +
            "inner join products p on p.id = sd.product_id\n" +
            "where date(s.sold_date) >= :startDate and date(s.sold_date) <= :endDate\n" +
            "group by p.id, p.name"+
            "",nativeQuery = true)

    List<ProductSold>findProductSold(LocalDate startDate, LocalDate endDate);
}
