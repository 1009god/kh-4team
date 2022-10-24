package com.kh.doran.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.doran.entity.MemDto;
import com.kh.doran.repository.MemDao;

@Controller
@RequestMapping("/edit")
public class EditController {

	@Autowired
	private MemDao memDao;
	
	
	//프로필 정보 수정
		// 1. 자신의 현재 정보를 조회하여 화면에 출력
		// 2. 바꾸고 싶은 정보를 입력하여 전송하면 해당 정보를 변경

		@GetMapping("/profile")
		public String editProfile(HttpSession session, Model model) {
			
			//(1)아이디 획득(HttpSession)
			int memNo = (int)session.getAttribute("loginNo");
			
			//(2) 아이디로 정보를 조회
			MemDto memDto = memDao.selectOne(memNo);
			
			//(3) 조회한 정보를 화면으로 전달
			model.addAttribute("memDto",memDto);
			
			//(4) 연결될 화면 주소를 반환
			return "edit/profile";
		}
		
		@PostMapping("/profile")
		public String editProfile(HttpSession session, 
												@ModelAttribute MemDto inputDto, //client가 입력한 값
												RedirectAttributes attr) {
			// memberNo는 input으로 받는것이 없음-> session에서 꺼내온다 -> 추가 설정을 해야함
			int memNo = (int)session.getAttribute("loginNo");
			inputDto.setMemNo(memNo); //memberDto에 세션에서 가져온 memNo를 넣어줌  // 지금 사용자의 no
			
			boolean result = memDao.editProfile(inputDto);
			
			if(result) {			
				attr.addAttribute("memNo",inputDto.getMemNo());	
				return "redirect:/edit/profile";	
			}
		
			else {
				return "redirect:/edit/editFail";
			}	
		}


		@GetMapping("/profile_result")
		public String infromaitonResult() {
			return "profile";
		}		
		
		
	//account 정보 수정
	// 비밀번호 수정
	// 전화번호 수정	
		@GetMapping("/account")
		public String editAccount(HttpSession session, Model model) {
			
			//(1)아이디 획득(HttpSession)
			int memNo = (int)session.getAttribute("loginNo");
			
			//(2) 아이디로 정보를 조회
			MemDto memDto = memDao.selectOne(memNo);
			
			//(3) 조회한 정보를 화면으로 전달
			model.addAttribute("memDto",memDto);
			
			//(4) 연결될 화면 주소를 반환
			return "edit/account";
		}
		
		@PostMapping("/account")
		public String editAccount(HttpSession session, 
												@ModelAttribute MemDto inputDto, //client가 입력한 값
												RedirectAttributes attr) {
			// memberNo는 input으로 받는것이 없음-> session에서 꺼내온다 -> 추가 설정을 해야함
			int memNo = (int)session.getAttribute("loginNo");
			inputDto.setMemNo(memNo); //memberDto에 세션에서 가져온 memNo를 넣어줌  // 지금 사용자의 no
			
			boolean result = memDao.editAccount(inputDto);
			
			if(result) {			
				attr.addAttribute("memNo",inputDto.getMemNo());	
				return "redirect:/edit/account";	
			}
		
			else {
				return "redirect:/edit/editFail";
			}	
		}

		@GetMapping("/account_result")
		public String accountResult() {
			return "edit/account";
		}
		
		
		
}
