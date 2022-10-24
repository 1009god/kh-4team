package com.kh.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.doran.entity.PjDto;
import com.kh.doran.repository.AdminPjDao;

@Controller
@RequestMapping("/admin")
public class AdminPjController {

	@Autowired
	private AdminPjDao adminPjDao;
	
	
	@RequestMapping("/pjlist")
	public String list(Model model,
			@RequestParam(required = false) String type, 
			@RequestParam(required = false) String keyword) {
		boolean isSearch = type != null && keyword != null;
		if(isSearch) {
			model.addAttribute("list",adminPjDao.selectList(type, keyword));
		}
		else {
			model.addAttribute("list",adminPjDao.selectList());
		}
		return "admin/pjlist";
	}
	
	@GetMapping("/pjdetail")
	public String detail(Model model,@RequestParam int pjNo) {
		PjDto pjDto = adminPjDao.selectOne(pjNo);
		model.addAttribute("pjDto",pjDto);
		
		return "admin/pjdetail";
		
	}
	
	@GetMapping("/pjdelete")
	public String delete(@RequestParam int pjNo) {
		boolean result = adminPjDao.delete(pjNo);
		if(result) {
			return "redirect:pjlist";
		}
		else {
			return "admin/editPjFail";
		}
	}
	
}
