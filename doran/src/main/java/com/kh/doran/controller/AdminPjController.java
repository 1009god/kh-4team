package com.kh.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.doran.entity.PjDto;
import com.kh.doran.repository.AdminPjDao;
import com.kh.doran.repository.PjDao;
import com.kh.doran.vo.PjListSearchVO;

@Controller
@RequestMapping("/admin")
public class AdminPjController {

	@Autowired
	private AdminPjDao adminPjDao;
	
	@Autowired
	private PjDao pjDao;
	
	
	@RequestMapping("/pjlist")
	public String list(Model model, 
			@ModelAttribute(name="pjListSearchVo") PjListSearchVO vo) {
		
		//페이지 네비게이터를 위한 게시글 수를 구한 것
		int count = pjDao.count(vo);
		vo.setCount(count);
	
		model.addAttribute("list",pjDao.selectList(vo));
		return "admin/pjlist";
	};
	
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
