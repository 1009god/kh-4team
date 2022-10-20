package com.kh.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.doran.entity.MemDto;
import com.kh.doran.repository.MemDao;

@Controller
@RequestMapping("/mem")
public class MemController {
	@Autowired
	private MemDao memDao;
	
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
		return "mem/joinFinish"; //축하드린다는 창
	}
	
	@GetMapping("/login")
	public String login() {
		return "mem/login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute MemDto inputDto) {
		MemDto findDto = memDao.selectOne(inputDto.getMemEmail());
		if(findDto == null) { //아이디 틀리면 로그인창
			return "redirect:login?error"; 
		}
		
		boolean passwordMatch = 
				inputDto.getMemPw().equals(findDto.getMemPw());
		if(passwordMatch) {
			return "redirect:/"; //홈페이지로 보내주자 맞으면
		}
		else {
			return "redirect:login?error"; //리다이렉트 = 겟, 비번 틀리면 로그인창
		}
		
	}

}
