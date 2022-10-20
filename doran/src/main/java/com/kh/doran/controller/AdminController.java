package com.kh.doran.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.doran.constant.SessionConstant;
import com.kh.doran.entity.AdminDto;
import com.kh.doran.repository.AdminDao;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminDao adminDao;
	
	@GetMapping("/insert")
	public String insert() {
		return "/admin/insert";
	}
	
	@PostMapping("/insert")
	public String insert(@ModelAttribute AdminDto adminDto) {
	adminDao.insert(adminDto);
	return "redirect:insert_success";
	}
	@RequestMapping("insert_success")
	public String insertSuccess() {
		return "admin/insertResult";
	}
		
	@PostMapping("/login")
	public String login(@ModelAttribute AdminDto inputDto,
							HttpSession session) {
		AdminDto findDto=adminDao.selectOne(inputDto.getAdminEmail());
		if(findDto==null) {
			return "redirect:login?error";	
		}//inputDto는 사용자가 입력한 정보, findDto는 데이터베이스 조회 결과
		boolean passwordMatch=
				inputDto.getAdminEmail().equals(findDto.getAdminPw());
		if(passwordMatch) {
			session.setAttribute(SessionConstant.EMAIL,inputDto.getAdminEmail());
		
			adminDao.updateLoginTime(inputDto.getAdminEmail());
			return "redirect:/";
		}
		else {
			return "redirect:login?error";
		}
	}
}
