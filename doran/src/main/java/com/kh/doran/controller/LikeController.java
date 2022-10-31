package com.kh.doran.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.doran.repository.LikesDao;

@Controller

public class LikeController {
	
	@Autowired
	private LikesDao likesDao;
	
	@GetMapping("/like")
	public String like(HttpSession session,Model model) {
		//세션에서 멤버 번호 저장
		int likesMemNo = (int)session.getAttribute("loginNo");
		
		//좋아요 리스트
		model.addAttribute("likesList", likesDao.likeList(likesMemNo));	
		
		
		return "like/like";
	}
	
	
}
