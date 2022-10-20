package com.kh.doran.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.kh.doran.constant.SessionConstant;
import com.kh.doran.entity.LikesDto;
import com.kh.doran.repository.LikesDao;
import com.kh.doran.repository.MemDao;
import com.kh.doran.repository.OptionsDao;
import com.kh.doran.repository.PjDao;
import com.kh.doran.vo.PjListSearchVO;

@Controller
@RequestMapping("/pj")
public class PjController {
	
	
	
	@Autowired
	private PjDao pjDao;
	
	@Autowired
	private OptionsDao optionsDao;
	
	@Autowired
	private LikesDao likesDao;

	
	@GetMapping("/detail")

	public String detail(@RequestParam int pjNo, Model model, HttpSession session) {
		model.addAttribute("PjDto", pjDao.selectOne(pjNo));//프로젝트넘버로 검색해서 나온 값 model에 저장해서 넘김
		model.addAttribute("OptionsDto", optionsDao.selectList(pjNo));//pjno로 검색해서 나온 옵션들 model에 저장해서 넘김
		String loginId=(String) session.getAttribute(SessionConstant.EMAIL);
		//회원일 경우 좋아요 했는지 기록을 첨부
		if(loginId!=null) {
		LikesDto likesDto=new LikesDto();
		likesDto.setLikesPjNo(pjNo);
		likesDto.setLikesMemEmail(loginId);
		model.addAttribute("isLike", likesDao.check(likesDto));
		}

		return "pj/detail";
	};
	
	
	
	@GetMapping("/list")
	public String list(Model model, 
			@ModelAttribute(name="pjListSearchVo") PjListSearchVO vo) {
		
		if(vo.isSearch()) {
			model.addAttribute("list",pjDao.selectList(vo));
		}
		else {
			model.addAttribute("list", pjDao.selectList());
		}
		return "pj/list";
	};
	

}