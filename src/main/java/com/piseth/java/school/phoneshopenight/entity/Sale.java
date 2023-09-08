package com.piseth.java.school.phoneshopenight.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sales")
public class Sale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sale_id")
	private Long id;
	
	@Column(name = "date_import")
	private LocalDateTime dateImport;

	@Column(name = "sold_date")
	private LocalDateTime soldDate;

}