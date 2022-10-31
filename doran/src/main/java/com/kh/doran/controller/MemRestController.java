package com.kh.doran.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.doran.entity.MemDto;
import com.kh.doran.repository.MemDao;

//화면 없이 사용자 요청을 처리해서 데이터만 전송하는 컨트롤러


@CrossOrigin
@RestController //view 사용 x, 비동기식
@RequestMapping("/rest/mem")
public class MemRestController { //아이디 검사
	
	@Autowired
	private MemDao memDao;
	
	@GetMapping("/id")
	public String id(@RequestParam String memEmail) {
		MemDto memDto = memDao.selectOne(memEmail);
		if(memDto == null) {
			return "NNNNY"; //사용 가능 아이디 (당신이 첫 사람)
		}
		else {
			return "NNNNN";//사용 불가능 아이디 (이미 존재함)
		}
	}
	
	@RequestMapping("/nick")
	public String nick(@RequestParam String memNick) {
		MemDto memDto = memDao.findByNickname(memNick);
		if(memDto == null) {
			return "NNNNY"; //사용 가능 닉네임 (당신이 첫 사람)
		}
		else {
			return "NNNNN";//사용 불가능 닉네임 (이미 존재함)
		}
	}
	
//	@RequestMapping("/pw")
//	public String pw(@RequestParam String memPw, HttpSession session) {
//		 int memNo = (int)session.getAttribute("loginNo");  
//		 MemDto memDto = memDao.findByPw(memNo, memPw);
//		 if(memDto != null) { //디비가 비어있는게 아니면
//			 return "NNNNN"; //old 비밀번호가 일치
//		 }
//		 else {
//			 return "NNNNY"; // 비밀번호 불일치
//		 }
//	}
	
	@RequestMapping("/pw")
	public String pw(HttpSession session, @RequestParam int memPw) {
		 int memNo = (int)session.getAttribute("loginNo");  
		 MemDto memDto = memDao.selectOne(memNo);  //selectOne을 이용한 비밀번호 수정 비동기통신
		 
		 if(memDto.getMemPw().equals(memPw) ) { //입력한 pw와 디비에 들어있는 pw가 일치하면
			 return "NNNNN"; //old 비밀번호가 일치-> 수정 진행
		 }
		 else {
			 return "NNNNY"; // 비밀번호 불일치->수정 진행x
		 }
	}
	
	
}
