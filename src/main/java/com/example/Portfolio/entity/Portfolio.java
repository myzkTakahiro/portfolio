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
	
	private String email;
	
	private String password;
	
	private Date updateDate;
	
	private Date createDate;

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}





