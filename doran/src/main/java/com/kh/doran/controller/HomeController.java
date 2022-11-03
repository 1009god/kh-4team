package com.kh.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.doran.repository.AdminPjDao;
import com.kh.doran.repository.PjDao;
import com.kh.doran.vo.AdminpjListVO;
import com.kh.doran.vo.PjListSearchVO;

@Controller
public class HomeController {

	@Autowired
	private PjDao pjDao;
	@Autowired
	private AdminPjDao adminpjDao;
	
	@RequestMapping("${pageContext.request.contextPath}")
	public String home(Model model,
			@ModelAttribute(name="pjListSearchVo") PjListSearchVO vo
			,AdminpjListVO adminpjListvo) {
		int count = pjDao.count(vo);
		vo.setCount(count);
	
		model.addAttribute("pjlist",adminpjDao.selectList());
		model.addAttribute("list",pjDao.selectList(vo));
		
		return "home";
	}
	
	
}
