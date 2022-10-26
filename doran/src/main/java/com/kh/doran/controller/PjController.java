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

import com.kh.doran.entity.LikesDto;
import com.kh.doran.entity.OptionsDto;
import com.kh.doran.entity.OrdersDto;
import com.kh.doran.repository.AddressDao;
import com.kh.doran.repository.LikesDao;
import com.kh.doran.repository.MemDao;
import com.kh.doran.entity.AddressDto;
import com.kh.doran.entity.LikesDto;
import com.kh.doran.entity.PjDto;
import com.kh.doran.entity.MemDto;
import com.kh.doran.entity.OptionsDto;
import com.kh.doran.repository.LikesDao;
import com.kh.doran.repository.OptionsDao;
import com.kh.doran.repository.OrdersDao;
import com.kh.doran.repository.PjDao;
import com.kh.doran.vo.OrdersCalVO;
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
	
	@Autowired
	private MemDao memDao;
	
	@Autowired
	
	
	private OrdersCalVO ordersCalVo;


	private AddressDao addressDao;
	
	@Autowired
	private OrdersDao ordersDao;

	
	@GetMapping("/detail")
	public String detail(@RequestParam int pjNo, Model model, HttpSession session) {
		model.addAttribute("PjDto", pjDao.selectOne(pjNo));//프로젝트넘버로 검색해서 나온 값 model에 저장해서 넘김
		model.addAttribute("OptionsDto", optionsDao.selectList(pjNo));//pjno로 검색해서 나온 옵션들 model에 저장해서 넘김

		
		Integer loginNo=(Integer) session.getAttribute("loginNo");
		if(loginNo==null) {
			return "pj/detail";
		}
		else {
			LikesDto likesDto=new LikesDto();
			likesDto.setLikesMemNo(loginNo);
			likesDto.setLikesPjNo(pjNo);
			model.addAttribute("check",likesDao.check(likesDto));
		}
		return "pj/detail";
	};
	
	
	@GetMapping("/selectCheck")//구매할 옵션 선택(확인)
	public String selectCheck(@RequestParam int optionsNo, Model model, HttpSession session) {
		Integer loginNo=(Integer) session.getAttribute("loginNo");
		if(loginNo==null) {
			return "redirect:/mem/login";
		}
		
		model.addAttribute("OptionsDto", optionsDao.selectOne(optionsNo));//선택중인 옵션
		return "pj/selectCheck";
	};
	
	@GetMapping("/order")
	public String order(@RequestParam int optionsNo, HttpSession session, Model model) {
		Integer loginNo=(Integer) session.getAttribute("loginNo");
		if(loginNo==null) {
			return "redirect:/mem/login";
		}
		OptionsDto optionsDto=optionsDao.selectOne(optionsNo);
		int pjNo=optionsDto.getOptionsPjNo();
		model.addAttribute("OptionsDto", optionsDao.selectOne(optionsNo));
		model.addAttribute("PjDto", pjDao.selectOne(pjNo));

		//현재 접속중인 계정이 등록해둔 배송지 목록을 저장해서 넘김
		int loginNo2=(int) session.getAttribute("loginNo");
		model.addAttribute("AddressDto",addressDao.selectList(loginNo2));
		
		
		return "pj/order";
	};
	
	
	@PostMapping("/order")
	public String order(@ModelAttribute OrdersDto ordersDto, @ModelAttribute AddressDto addressDto,
			@RequestParam int ordersOptionsNo, Model model, HttpSession session) {
		Integer loginNo=(Integer) session.getAttribute("loginNo");
		if(loginNo==null) {
			return "redirect:/mem/login";
		}
		int loginNo2=(int) session.getAttribute("loginNo");
		model.addAttribute("memNo", loginNo2);
		OptionsDto optionsDto=optionsDao.selectOne(ordersOptionsNo);
		int optionsPjNo=optionsDto.getOptionsPjNo();
		model.addAttribute("PjDto", pjDao.selectOne(optionsPjNo));
		model.addAttribute("OptionsDto", optionsDao.selectOne(ordersOptionsNo));
		ordersDao.insert(ordersDto);//주문 작성
		optionsDao.stockUpdate(ordersOptionsNo);//주문한 옵션의 재고를 1 깎음
		return "redirect:/pj/orderComplete";
	};
	
	@GetMapping("/orderComplete")
	public String orderComplete() {
		return "pj/orderComplete";
	}
	
	@GetMapping("/like")
	public String like(@RequestParam int pjNo, HttpSession session, RedirectAttributes attr) {
		int loginNo=(int) session.getAttribute("loginNo");
		LikesDto likesDto=new LikesDto();
		likesDto.setLikesMemNo(loginNo);
		likesDto.setLikesPjNo(pjNo);
		
		if(likesDao.check(likesDto)) {//이미 좋아요를 했을 경우
			likesDao.delete(likesDto);
		}
		else {//아직 좋아요를 하지 않았다면
			likesDao.insert(likesDto);
		}
		
		likesDao.refresh(pjNo);//좋아요 수를 갱신함
		attr.addAttribute("pjNo",pjNo);
		return "redirect:/pj/detail";
		
	};
	
	
	@GetMapping("/list")
	public String list(Model model, 
			@ModelAttribute(name="pjListSearchVo") PjListSearchVO vo) {
		
		//페이지 네비게이터를 위한 게시글 수를 구한 것
		int count = pjDao.count(vo);
		vo.setCount(count);
	
		model.addAttribute("list",pjDao.selectList(vo));
		model.addAttribute("amountCalList", pjDao.achievementRate());
		return "pj/list";
	};
	
	

}