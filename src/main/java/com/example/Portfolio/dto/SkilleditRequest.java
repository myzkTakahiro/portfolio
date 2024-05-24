package com.example.Portfolio.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class SkilleditRequest implements Serializable {
	
	private Long id;
	
	private String name;
	
	private Long category_id;

}
