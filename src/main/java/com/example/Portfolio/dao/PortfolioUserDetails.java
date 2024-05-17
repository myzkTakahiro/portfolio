package com.example.Portfolio.dao;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.example.Portfolio.entity.users;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PortfolioUserDetails extends User {

	private String name;
	
	public PortfolioUserDetails(String email, String password,  Collection<? extends GrantedAuthority> authorities, String name) {
		super(email, password, authorities);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}