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

}
