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
	private Long id;
	private String self_introduction;
	
	public PortfolioUserDetails(String email, String password,  Collection<? extends GrantedAuthority> authorities, String name, Long id, String self_introduction) {
		super(email, password, authorities);
		this.name = name;
		this.id = id;
		this.self_introduction = self_introduction;
	
	}
	
	public String getName() {
		return name;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getSelfIntroduction() {
		return self_introduction;
	}


}