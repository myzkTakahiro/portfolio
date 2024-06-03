package com.example.Portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Portfolio.dao.PortfolioMapper;
import com.example.Portfolio.dto.SkillNewAddRequest;
import com.example.Portfolio.dto.SkillTimeUpdateRequest;
import com.example.Portfolio.entity.Categories;
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
	    
	    
	    public void add(SkillNewAddRequest skillNewAddRequest) {
	    	learningdataMapper.add(skillNewAddRequest);
	    }
	    
	    
	    public boolean isItemExist(String name) {
			
			LearningData existingItem = learningdataMapper.findName(name);
			return existingItem != null;
		}
	    
	    public void timeUpdate(SkillTimeUpdateRequest skillTimeUpdateRequest) {
	    	learningdataMapper.timeUpdate(skillTimeUpdateRequest);
	    }
	    
	    public Categories findCategory(Integer category_id) {
	    	return learningdataMapper.findCategory(category_id);
	    }
	    
	    public void delete(Long id) {
	        learningdataMapper.delete(id);
	    }


}
