package com.kh.doran.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.doran.entity.MemDto;
import com.kh.doran.entity.SellerDto;
import com.kh.doran.repository.MemDao;
import com.kh.doran.repository.SellerDao;


@Controller
@RequestMapping("/mem")
public class MemController {
	@Autowired
	private MemDao memDao;
	@Autowired
	private SellerDao sellerDao;
	
	
	
	@GetMapping("/join") 
	public String join() {
		return "mem/join";
	}
	@PostMapping("/join")
	public String join(@ModelAttribute MemDto memDto) {
		memDao.insert(memDto);
		return "redirect:join_finish";
	}
	
	@GetMapping("/join_finish")
	public String joinFinish() {
		return "mem/joinFinish"; //완료 페이지는 홈으로? 아님 축하드린다는 창?
	}
	
	@GetMapping("/login")
	public String login() {
		return "mem/login";
	}
	
	@PostMapping("/login")
	public String login(
			@ModelAttribute MemDto inputDto, //사용자가 입력한 정보
			HttpSession session) {
		MemDto findDto = memDao.selectOne(inputDto.getMemEmail()); //사용자가 입력한 이메일을 인풋dto에 담아서 Dao에 있는 이메일로 단일조회하는 기능을 쓴다(db를 가는거다)
		
		
		if(findDto == null) { //아이디 틀리면 로그인창
			return "redirect:login?error"; 
		}
		
		SellerDto sellerfindDto = sellerDao.selectOne(findDto.getMemNo()); //인풋dto를 통해 아이디(이메일)로 셀러를 조회한 것에서 
		
		
		boolean passwordMatch = inputDto.getMemPw().equals(findDto.getMemPw());
		if(passwordMatch) {	//비밀번호가 맞으면
			if(sellerfindDto==null) { // 검색해서 나온 결과가 없으면 
				session.setAttribute("loginId", inputDto.getMemEmail()); //loginId = 회원 이메일을 이 이름으로 저장 세션 셋 어쩌구 ("이름", "값); 회원 번호만 세션에 넣음
				
				session.setAttribute("loginNo", findDto.getMemNo()); //loginNo = 회원 번호 세션에 저장
				return "redirect:/";
			}
			session.setAttribute("loginId", inputDto.getMemEmail()); //loginId = 회원 이메일을 이 이름으로 저장 세션 셋 어쩌구 ("이름", "값); 회원 번호만 세션에 넣음
			//session.setAttribute("loginEmail", inputDto.getMemEmail());
			session.setAttribute("loginNo", findDto.getMemNo()); //loginNo = 회원 번호 세션에 저장
			
			session.setAttribute("sellerNo", sellerfindDto.getSellerMemNo()); //sellerNo란 이름에 판매자 번호를 세션에 저장
			return "redirect:/"; //홈페이지로 보내주자 맞으면
		}
		else {
			return "redirect:login?error"; //리다이렉트 = 겟, 비번 틀리면 로그인창
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginId"); //세션에 loginId 라는 이름의 데이터 삭제
		session.removeAttribute("loginNo"); //세션에 loginNo 라는 이름의 데이터 삭제
		session.removeAttribute("sellerNo");
		return "redirect:/"; //메인페이지로 강제 이동
	}
	
	
}
