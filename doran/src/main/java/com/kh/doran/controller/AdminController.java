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

import com.kh.doran.entity.AdminDto;
import com.kh.doran.entity.MemDto;
import com.kh.doran.repository.AdminDao;
import com.kh.doran.repository.MemDao;
import com.kh.doran.repository.PjDao;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private MemDao memDao;
	
	@Autowired
	private PjDao pjDao;
	
//	@Autowired
//	private SellerDao sellerDao;
	
	

	@GetMapping("/insert")
	public String insert() {
		return "/admin/insert";
	}
	
	@PostMapping("/insert")
	public String insert(@ModelAttribute AdminDto adminDto) {
	adminDao.insert(adminDto);
	return "redirect:insert_success";
	}
	@RequestMapping("insert_success")
	public String insertSuccess() {
		return "admin/insertResult";
	}
	
	@GetMapping("/login")//로그인 정보가 맞지않다고 뜨는 오류 해결해야함ㅠㅠ
	public String login() {
		return "admin/login";
	}
		
	@PostMapping("/login")
	public String login(@ModelAttribute AdminDto inputDto,
							HttpSession session) {
		AdminDto findDto=adminDao.selectOne(inputDto.getAdminEmail());
		if(findDto==null) {
			return "redirect:login?error";	
		}//inputDto는 사용자가 입력한 정보, findDto는 데이터베이스 조회 결과
		boolean passwordMatch=
				inputDto.getAdminPw().equals(findDto.getAdminPw());
		if(passwordMatch) {
			session.setAttribute("loginNo",inputDto.getAdminEmail());
		
			adminDao.updateLoginTime(inputDto.getAdminEmail());
			return "redirect:/";
		}
		else {
			return "redirect:login?error";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginNo");
		
		return "redirect:/";
	}
	
////	조회 기능
//	@GetMapping("/list")
//	public String list(Model model) {
//		List<MemDto> list = AdminDao.selecList;
//		model.addAttribute("list", list);
//		return "admin/memlist";
//	}
	
	
	@GetMapping("/memlist")
	public String list(Model model,
				@RequestParam(required=false)String type,
				@RequestParam(required=false)String keyword) {
		boolean isSearch=type!=null&&keyword!=null;
		if(isSearch) {
			model.addAttribute("list",adminDao.selectList(type, keyword));			
		}
		else {
			model.addAttribute("list",adminDao.selectList());
		}
		return "admin/memlist";
	}
	
	@GetMapping("/detail")
	public String detail(Model model,
						@RequestParam int memNo) {
		MemDto memDto=memDao.selectOne(memNo);
		return "mem/detail";
	}

//	@GetMapping("list")
//	public String list(Model model,
//						@ModelAttribute(name="vo")MemListVO vo) {
//		
//		int count = adminDao.count(vo);
//	}

	@GetMapping("/change")
	public String change(Model model,@RequestParam int memNo) {
		model.addAttribute("memDto",memDao.selectOne(memNo));
		return "mem/change";
	}

	
//	@GetMapping("/detail")
//	public String detail(Model model,
//						@RequestParam String memEmail) {
//		MemDto memDto=memDao.selectOne(memEmail);
//		return "admin/detail";
//
//	}
	
//	@GetMapping("/change")
//	public String change(Model model,@RequestParam String memEmail) {
//		model.addAttribute("memDto",memDao.selectOne(memEmail));
//		return "admin/change";
//
//	}
//	

//	@PostMapping("/change")
//	public String change(@ModelAttribute MemDto memDto,RedirectAttributes attr){
//		boolean result = adminDao.update(memDto);
//		if(result) {
//			attr.addAttribute("memEmail",memDto.getMemEmail());	
//			return "redirect:detail";
//			}
//		else {
//			return "redirect:change_fail";
//		}
//	}
//	@GetMapping("/change_fail")
//	public String changeFail() {
//
//		return "admin/changeFail";
//	}
	
	
	
	
}
