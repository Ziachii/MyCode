package com.piseth.java.school.phoneshopenight.service;

import com.piseth.java.school.phoneshopenight.dto.ProductReportDTO;
import com.piseth.java.school.phoneshopenight.dto.report.ExpenseReportDTO;
import com.piseth.java.school.phoneshopenight.projection.ProductSold;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
    List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate);
    List<ExpenseReportDTO> getExpenseReport(LocalDate startDate, LocalDate endDate);


}
