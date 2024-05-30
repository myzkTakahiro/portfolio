package com.example.Portfolio.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.Portfolio.dto.PortfolioAddRequest;
import com.example.Portfolio.dto.PortfolioUpdateRequest;
import com.example.Portfolio.dto.SkillNewAddRequest;
import com.example.Portfolio.dto.SkillTimeUpdateRequest;
import com.example.Portfolio.entity.Categories;
import com.example.Portfolio.entity.LearningData;
import com.example.Portfolio.entity.users;



@Mapper
public interface PortfolioMapper {
	
	List<users> findAll();
	
	users findById(Long id);
	
	void save(PortfolioAddRequest portfolioRequest);
	
	void update(PortfolioUpdateRequest portfolioUpdateRequest);

	public users findByEmail(String email);
	
	List<LearningData> Allfind();
	
	LearningData Idfind(Long id);
	
	public LearningData findByName(String nmae);
	
	void add(SkillNewAddRequest skillNewAddRequest);
	
	public LearningData findName(String name);
    
	void timeUpdate(SkillTimeUpdateRequest skillTimeUpdateRequest);
	
	public Categories findCategory(Integer category_id);

}


