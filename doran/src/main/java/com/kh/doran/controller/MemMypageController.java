package com.kh.doran.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.doran.entity.MemDto;
import com.kh.doran.repository.MemDao;



@Controller
@RequestMapping("/mypage")
public class MemMypageController {
	
	@Autowired
	private MemDao memDao;
	
	//프로필 홈
  @GetMapping("/profile")
  public String mypage(HttpSession session, Model model) {
     //1. 세션에 들어있는 아이디를 꺼낸다 (down casting다운캐스팅) 형변환?
     //- 세션에 저장된 형태가 Object이기 때문에 string으로 다운캐스팅
     String memEmail = (String) session.getAttribute("loginId");
     
     
     //2. 아이드를 이용하여 회원정보를 불러온다
     MemDto memDto = memDao.selectOne(memEmail);
     
     //3.불러온 정보를 모델에 첨부한다
     model.addAttribute("memDto", memDto);
     
     //4.화면(view)으로 전달(forward)한다
     
     return "mypage/profile";
  }

  	//수정
	@GetMapping("/edit_profile")
	public String edit(Model model, @RequestParam String memNo) {
		MemDto dto = memDao.selectOne(memNo);
		model.addAttribute("memDto", dto);
		return "mypage/editProfile";
	}
	
	@PostMapping("/edit_profile")
	public String edit(@ModelAttribute MemDto memDto,
									RedirectAttributes attr) {
		boolean result = memDao.profileUpdate(memDto);
		if(result) {
			attr.addAttribute("memNo", memDto.getMemNo());
			return "redirect:mypage/editProfile";
		}
		else {
			return "redirect:edit_fail";
		}
	}
	
	@GetMapping("/edit_fail")
	public String editFail() {
		return "mypage/editFail";
	}
}
