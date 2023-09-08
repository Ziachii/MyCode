package com.piseth.java.school.phoneshopenight.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "saleDetails")
public class SaleDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sale_detial_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "sale_id")
	private Sale sale;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "amount")
	protected BigDecimal amount;

	@Column(name = "unit")
	private Integer unit;

}