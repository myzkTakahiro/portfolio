package com.example.Portfolio.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
public class users implements Serializable {
	
	
	private  Long id;
	
	private  String name;
	
	protected  String email;
	
	private  String password;
	
	private String self_introduction;
	
	private Date updated_at;
	
	private Date created_at;
	
	
	
    
    
    
    
    
    
    
}