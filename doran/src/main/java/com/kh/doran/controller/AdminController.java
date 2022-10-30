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
import com.kh.doran.repository.FilesDao;
import com.kh.doran.repository.MemDao;
import com.kh.doran.repository.PjDao;
import com.kh.doran.vo.MemListSearchVO;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private MemDao memDao;
	
	@Autowired
	private PjDao pjDao;
	
	@Autowired
	private FilesDao filesDao;
	
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
			session.setAttribute("loginId",inputDto.getAdminEmail());
			session.setAttribute("loginNo", findDto.getAdminNo()); 
//			adminDao.updateLoginTime(inputDto.getAdminEmail());
			return "redirect:/admin";
		}
		else {
			return "redirect:login?error";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginId");
		session.removeAttribute("loginNo");
		
		return "redirect:/admin";
	}
	
////	조회 기능
//	@GetMapping("/list")
//	public String list(Model model) {
//		List<MemDto> list = AdminDao.selecList;
//		model.addAttribute("list", list);
//		return "admin/memlist";
//	}
	
	//회원 리스트
	@GetMapping("/memlist")
	public String list(Model model,HttpSession session, 
			@ModelAttribute(name="vo") MemListSearchVO vo) {
		
		//페이지 네비게이터를 위한 게시글 수를 구한 것
		int count = adminDao.count(vo);
		vo.setCount(count);
	
		model.addAttribute("list",adminDao.selectList(vo));
		if(session.getAttribute("loginNo")!=null) {
			return "admin/memlist";			
		}
		else {
			return "admin/login";
		}
	};
	
	@GetMapping("/detail")
	public String detail(Model model,
						@RequestParam int memNo) {
		MemDto memDto=adminDao.selectOne1(memNo);
		model.addAttribute("memDto",memDto);
		
		//(+추가) 프로필 이미지
	     model.addAttribute("profileImg", filesDao.profileImgList(memNo));
		
		return "admin/detail";
	}

	@GetMapping("/change")
	public String change(Model model,@RequestParam int memNo) {
		model.addAttribute("memDto",adminDao.selectOne1(memNo));
		return "admin/change";

	}
	

	@PostMapping("/change")
	public String change(@ModelAttribute MemDto memDto,RedirectAttributes attr){
		boolean result = adminDao.update(memDto);
		if(result) {
			attr.addAttribute("memNo",memDto.getMemNo());	
			return "redirect:admin/detail";
			}
		else {
			return "redirect:admin/change_fail";
		}
	}
	@GetMapping("/change-fail")
	public String changeFail() {

		return "admin/changeFail";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam int memNo) {
		boolean result = adminDao.delete(memNo);
		if(result) {
			return "redirect:memlist";
		}
		else {
			return "admin/editFail";
		}
	}

	
	
	
}
