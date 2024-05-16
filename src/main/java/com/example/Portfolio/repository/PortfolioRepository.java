package com.example.Portfolio.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Portfolio.entity.Portfolio;

@Repository
public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {
	
	Optional<Portfolio> findByEmail(String email); 

	

}