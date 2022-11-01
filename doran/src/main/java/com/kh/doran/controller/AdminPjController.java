package com.kh.doran.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.doran.entity.LikesDto;
import com.kh.doran.entity.PjDto;
import com.kh.doran.repository.AdminPjDao;
import com.kh.doran.repository.LikesDao;
import com.kh.doran.repository.OptionsDao;
import com.kh.doran.repository.PjDao;
import com.kh.doran.repository.PjFileDao;
import com.kh.doran.vo.OrderCountVO;
import com.kh.doran.vo.PjListSearchVO;

@Controller
@RequestMapping("/admin")
public class AdminPjController {

	@Autowired
	private AdminPjDao adminPjDao;
	
	@Autowired
	private PjDao pjDao;
	
	@Autowired
	private LikesDao likesDao;
	
	@Autowired
	private OptionsDao optionsDao;
	
	@Autowired
	private PjFileDao pjFileDao;
	
	@RequestMapping("/pjlist")
	public String list(Model model,HttpSession session,
			@ModelAttribute(name="pjListSearchVo") PjListSearchVO vo) {
		
		//페이지 네비게이터를 위한 게시글 수를 구한 것
		int count = pjDao.count(vo);
		vo.setCount(count);
		model.addAttribute("list",adminPjDao.selectList(vo));
		//세션 추가후 관리자번호 불러오는 if문 추가(관리자 기능 로그인 이후 사용 가능)
		if(session.getAttribute("loginNo")!=null) {
			return "admin/pjlist";			
		}
		else {
			return "admin/login";
		}
	};
	
	@GetMapping("/pjdetail")
	public String detail(@RequestParam int pjNo, @ModelAttribute OrderCountVO vo, Model model, HttpSession session) {
		model.addAttribute("PjDto", pjDao.selectOne(pjNo));//프로젝트넘버로 검색해서 나온 값 model에 저장해서 넘김
		model.addAttribute("OptionsDto", optionsDao.selectList(pjNo));//pjno로 검색해서 나온 옵션들 model에 저장해서 넘김
		model.addAttribute("OrdersCalVO", pjDao.calVo(pjNo));//선택한 프로젝트의 총 결제금액, 달성율
		model.addAttribute("OrderCountAll", pjDao.orderCountAll(pjNo));//이 프로젝트를 구입한 회원 명수
		model.addAttribute("DateCount", pjDao.dateCount(pjNo));//마감일까지 며칠 남았는지(date로는 못 받고 float치환)
		model.addAttribute("PjFileList", pjFileDao.pjFileList(pjNo));//프로젝트 대표이미지들
		
		//프로젝트개설판매자의  회원테이블을 넘김
		PjDto pjDto=pjDao.selectOne(pjNo);
//		int sellerNo=pjDto.getPjSellerMemNo();
//		model.addAttribute("Seller",memDao.selectOne(sellerNo));
				
		Integer loginNo=(Integer) session.getAttribute("loginNo");
		if(loginNo==null) {
			return "admin/pjdetail";
		}
		else {
			LikesDto likesDto=new LikesDto();
			likesDto.setLikesMemNo(loginNo);
			likesDto.setLikesPjNo(pjNo);
			model.addAttribute("check",likesDao.check(likesDto));
			vo.setOptionsPjNo(pjNo);
			int loginNo2=(int) session.getAttribute("loginNo");
			vo.setOrdersMemNo(loginNo2);
			model.addAttribute("OrderCount", pjDao.orderCount(vo));//구매여부
		}
		return "admin/pjdetail";
	};
	
	@GetMapping("/pjdelete")
	public String delete(@RequestParam int pjNo) {
		boolean result = adminPjDao.delete(pjNo);
		if(result) {
			return "redirect:pjlist";
		}
		else {
			return "admin/editPjFail";
		}
	}
	
	
	
}
