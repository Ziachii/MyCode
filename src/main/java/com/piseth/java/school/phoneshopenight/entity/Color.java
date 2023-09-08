package com.piseth.java.school.phoneshopenight.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "colors")
public class Color {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "color_id")
	private Long id;
	
	@Column(name = "color_name")
	private String name;
}