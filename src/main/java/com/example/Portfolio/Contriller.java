package com.example.Portfolio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Contriller {
	
	@RequestMapping("/")
	public String add() {
		return "add";
	}

}
