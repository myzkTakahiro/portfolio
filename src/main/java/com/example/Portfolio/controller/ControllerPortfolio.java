package com.example.Portfolio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Portfolio.dto.PortfolioAddRequest;
import com.example.Portfolio.entity.Portfolio;
import com.example.Portfolio.service.PortfolioService;


@Controller
public class ControllerPortfolio {
	
	@Autowired
    private PortfolioService userInfoService;
	
	
	 @GetMapping(value = "/user/add")
	    public String displayAdd(Model model) {
	        model.addAttribute("portfolioAddRequest", new PortfolioAddRequest());
	        return "user/add";
	    }
	
	 @RequestMapping(value = "/user/create", method = RequestMethod.POST)
	    public String create(@Validated @ModelAttribute PortfolioAddRequest portfolioRequest, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            // 入力チェックエラーの場合
	            List<String> errorList = new ArrayList<String>();
	            for (ObjectError error : result.getAllErrors()) {
	                errorList.add(error.getDefaultMessage());
	            }
	            model.addAttribute("validationError", errorList);
	            return "user/add";
	        }
	        // ユーザー情報の登録
	        userInfoService.save(portfolioRequest);
	        return "redirect:/user/list";
	    }

}
