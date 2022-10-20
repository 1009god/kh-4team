package com.kh.doran.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.doran.entity.MemDto;

@Controller
@RequestMapping("/mypage")
public class MemProfileController {
	
//	@GetMapping("/profile")
//	public String mypage(HttpSession session, Model model) {
//		//1. 세션에 들어있는 아이디를 꺼낸다 (down casting다운캐스팅) 형변환?
//		//- 세션에 저장된 형태가 Object이기 때문에 string으로 다운캐스팅
//		String memEmail = (String) session.getAttribute("memEmail");
//		
//		
//		//2. 아이드를 이용하여 회원정보를 불러온다
//		MemDto memDto = memDao.selectOne(memEmail);
//		
//		//3.불러온 정보를 모델에 첨부한다
//		model.addAttribute("memDto", memDto);
//		
//		//4.화면(view)으로 전달(forward)한다
//		
//		return "mypage/profile";
//	}
	
}
