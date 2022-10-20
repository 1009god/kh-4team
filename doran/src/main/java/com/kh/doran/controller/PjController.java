package com.kh.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.kh.doran.repository.OptionsDao;
import com.kh.doran.repository.PjDao;

@Controller
@RequestMapping("/pj")
public class PjController {
	
	@Autowired
	private PjDao pjDao;
	
	@Autowired
	private OptionsDao optionsDao;
	
	@GetMapping("/detail")
	public String detail(@RequestParam int pjNo, Model model) {
		model.addAttribute("PjDto", pjDao.selectOne(pjNo));
		model.addAttribute("OptionsDto", optionsDao.selectList(pjNo));
		return "pj/detail";
	};
	
	
	

}
