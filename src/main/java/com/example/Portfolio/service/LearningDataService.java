package com.example.Portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Portfolio.dao.PortfolioMapper;
import com.example.Portfolio.dto.SkillnewAddRequest;
import com.example.Portfolio.entity.LearningData;

@Service
public class LearningDataService {
	
	@Autowired
    private PortfolioMapper learningdataMapper;
	
	 public List<LearningData> Allfind() {
	        return learningdataMapper.Allfind();
	    }

	    
	    public LearningData Idfind(Long id) {
	        return learningdataMapper.Idfind(id);
	    }

	    public LearningData findByName(String name) {
	    	return learningdataMapper.findByName(name);
	    }
	    
	    
	    public void add(SkillnewAddRequest skillnewaddRequest) {
	    	learningdataMapper.add(skillnewaddRequest);
	    }
	    

}
