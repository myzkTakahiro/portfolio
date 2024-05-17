package com.example.Portfolio.repository;

import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.example.Portfolio.entity.users;

@Repository
public interface PortfolioRepository extends CrudRepository<users, Long> {
	
	Optional<users> findByEmail(String  email); 

}
