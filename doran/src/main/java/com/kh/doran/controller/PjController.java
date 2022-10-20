package com.kh.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.doran.repository.PjDao;

@Controller
@RequestMapping("/pj")
public class PjController {
	
	@Autowired
	private PjDao pjDao;
	
//	@GetMapping("/detail")
//	public String detail(@RequestParam int pjNo, Model model) {
//		
//	};
	
	@GetMapping("/list")
	public String list(Model model, 
			@RequestParam(required = false) String type,
			@RequestParam(required = false) String keyword) {
		boolean isSearch = type!=null && keyword!=null;
		
		if(isSearch) {
			model.addAttribute("list",pjDao.selectList(type, keyword));
		}
		else {
			model.addAttribute("list", pjDao.selectList());
		}
		return "pj/list";
	};
	

}
