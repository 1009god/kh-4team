package com.kh.doran.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.doran.repository.AddressDao;
import com.kh.doran.repository.FilesDao;
import com.kh.doran.repository.LikesDao;
import com.kh.doran.repository.MemDao;
import com.kh.doran.repository.OptionsDao;
import com.kh.doran.repository.OrdersDao;
import com.kh.doran.repository.PjDao;
import com.kh.doran.service.Pjservice;
import com.kh.doran.vo.PjListSearchVO;

@Controller
public class HomeController {

	@Autowired
	private PjDao pjDao;
	
	@Autowired
	private OptionsDao optionsDao;
	
	@Autowired
	private LikesDao likesDao;
	
	@Autowired
	private MemDao memDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private FilesDao filesDao;
	@Autowired
	private Pjservice pjservice;

	
	
	
	@RequestMapping("/")
	public String home(Model model, HttpSession session,
			@ModelAttribute(name="pjListSearchVo") PjListSearchVO vo) {
		
		model.addAttribute("list",pjDao.selectList(vo));
		
//		model.addAttribute("list");
		return "home";
	}
	
	@RequestMapping("/pj/list")
	public String homeSearch(Model model, HttpSession session,
			@ModelAttribute(name="pjListSearchVo") PjListSearchVO vo) {
		
		model.addAttribute("list",pjDao.selectList(vo));
		
		return "home";
	}
}
