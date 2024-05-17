package com.example.Portfolio.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
public class users implements Serializable {
	
	
	private Long id;
	
	private  String name;
	
	protected  String email;
	
	private  String password;
	
	private Date updated_at;
	
	private Date created_at;
	
	public  String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public  String getEmail() {
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