package com.example.Portfolio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.Portfolio.dto.PortfolioAddRequest;
import com.example.Portfolio.dto.PortfolioSearchRequest;
import com.example.Portfolio.service.PortfolioService;

@RequestMapping("/")
@Controller
public class PortfolioController {
	
	@Autowired
    private PortfolioService portfolioService;
	
	@GetMapping(value = "/add")
	    public String displayAdd(Model model) {
	        model.addAttribute("portfolioAddRequest", new PortfolioAddRequest());
	        return "/user/add";
	    }
	
	 @GetMapping(value = "/top")
	 	public String displayAdd() {
		 
		 return "user/top";
	 }
	 

	 
	 
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
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
	        portfolioService.save(portfolioRequest);
	        return "redirect:/top";
	    }

	 @RequestMapping("/login")
	    public String search(Model model) {
		 	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        //Principalからログインユーザの情報を取得
	        String userName = auth.getName();
	        model.addAttribute("userName", userName);
	        return "user/login";
	    }


}