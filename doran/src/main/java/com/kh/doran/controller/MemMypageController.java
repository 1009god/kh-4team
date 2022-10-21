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
     
     return "mypage/edit/profile";
  }

//개인정보 수정
	// 1. 자신의 현재 정보를 조회하여 화면에 출력
	// 2. 바꾸고 싶은 정보를 입력하여 전송하면 해당 정보를 변경

	@GetMapping("/edit/profile")
	public String editProfile(HttpSession session, Model model) {
		
		//(1)아이디 획득(HttpSession)
		String memEmail = (String)session.getAttribute("loginId");
		
		//(2) 아이디로 정보를 조회
		MemDto memDto = memDao.selectOne(memEmail);
		
		//(3) 조회한 정보를 화면으로 전달
		model.addAttribute("memDto",memDto);
		
		//(4) 연결될 화면 주소를 반환
		return "mypage/edit/profile";
	}
	
	@PostMapping("/edit/profile")
	public String editProfile(HttpSession session, 
											@ModelAttribute MemDto inputDto, //client가 입력한 값
											RedirectAttributes attr) {
		// memberId는 input으로 받는것이 없음-> session에서 꺼내온다 -> 추가 설정을 해야함
		String memEmail = (String)session.getAttribute("loginId");
		inputDto.setMemEmail(memEmail); //memberDto에 세션에서 가져온 ID를 넣어줌
		
		//(1) 비밀번호를 검사
		MemDto findDto = memDao.selectOne(memEmail);  //findDto는 DB에서 가져온 값
		boolean passwordMatch = inputDto.getMemPw().equals(findDto.getMemPw());
		
		if(passwordMatch) {
			//(2) 비밀번호 검사를 통과했다면 정보를 변경하도록 처리
			memDao.editProfile(inputDto);
			return "redirect: mypage/profile";			
		}		
		else {
			return "redirect:error";
		}	
	}

	@GetMapping("/edit/profile_result")
	public String infromaitonResult() {
		return "mypage/profile";
	}
	
	
	
	
	
	
}
