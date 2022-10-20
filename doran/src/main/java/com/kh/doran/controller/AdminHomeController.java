package com.kh.doran.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class AdminHomeController {

	@RequestMapping("/admin")
	public String home(Model model) {
		
		return "AdminHome";
	}
}
