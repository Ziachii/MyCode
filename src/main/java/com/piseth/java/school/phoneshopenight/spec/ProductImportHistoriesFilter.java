package com.piseth.java.school.phoneshopenight.spec;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ProductImportHistoriesFilter {
    private LocalDate startDate;
    private LocalDate endDate;
}
