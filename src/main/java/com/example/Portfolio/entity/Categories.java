package com.example.Portfolio.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;


@Data
@Table(name="categories")
public class Categories implements Serializable {
	
	private Long id;
	
	private String name;
	

}
