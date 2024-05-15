package com.example.Portfolio.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;


@Data
public class Portfolio implements Serializable {
	
	
	private Long id;
	
	private String name;
	
	protected static String email;
	
	private static String password;
	
	private Date updateDate;
	
	private Date createDate;

	public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Portfolio.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Portfolio.password = password;
    }

}





