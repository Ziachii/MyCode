package com.piseth.java.school.phoneshopenight.dto;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductImportDTO {
    private Long productId;
    private Integer importUnit;
    private BigDecimal importPrice;
    private LocalDate importDate;
}
