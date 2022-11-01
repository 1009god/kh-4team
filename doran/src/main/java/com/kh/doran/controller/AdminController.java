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
import com.kh.doran.entity.DoranQDto;
import com.kh.doran.entity.MemDto;
import com.kh.doran.error.TargetNotFoundException;
import com.kh.doran.repository.AdminDao;
import com.kh.doran.repository.DoranQDao;
import com.kh.doran.repository.FilesDao;
import com.kh.doran.repository.MemDao;
import com.kh.doran.repository.PjDao;
import com.kh.doran.vo.DoranQListSearchVO;
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
	private DoranQDao doranQDao;
	
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
			session.setAttribute("AdminId",inputDto.getAdminEmail());
			session.setAttribute("AdminNo", findDto.getAdminNo()); 
//			adminDao.updateLoginTime(inputDto.getAdminEmail());
			return "redirect:/admin";
		}
		else {
			return "redirect:login?error";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("AdminId");
		session.removeAttribute("AdminNo");
		
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
		if(session.getAttribute("AdminNo")!=null) {
			return "admin/memlist";			
		}
		else {
			return "admin/login";
		}
	};
	
	@GetMapping("/detail")
	public String detail(Model model,HttpSession session,
						@RequestParam int memNo) {
		MemDto memDto=adminDao.selectOne1(memNo);
		model.addAttribute("memDto",memDto);
		
		//(+추가) 프로필 이미지
	     model.addAttribute("profileImg", filesDao.profileImgList(memNo));
		
	     if(session.getAttribute("AdminNo")!=null) {
				return "admin/detail";			
			}
			else {
				return "admin/login";
			}
		};


	@GetMapping("/change")
	public String change(Model model,@RequestParam int memNo,HttpSession session) {
		model.addAttribute("memDto",adminDao.selectOne1(memNo));
		if(session.getAttribute("AdminNo")!=null) {
			return "admin/change";			
		}
		else {
			return "admin/login";
		}
	};

	
	

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
	
	@GetMapping("doran-q/write")
	public String doranQWrite() {
		return "doranq/adminWrite";
	}
	
	@PostMapping("doran-q/write") 
	public String doranQWrite(
			@ModelAttribute DoranQDto doranQDto,
			/*HttpSession session,*/ RedirectAttributes attr) {
		//session 에 있는 회원 번호를 작성자로 추가한 뒤 등록해야 함
//		int memNo = (int)session.getAttribute("loginNo");
//		doranQDto.setDoranQMemNo(memNo);
		int doranQNo = doranQDao.sequence();
		doranQDto.setDoranQNo(doranQNo);
		
		DoranQDto parentDto = doranQDao.selectOne(
								doranQDto.getDoranQParent());
		doranQDto.setDoranQGroup(parentDto.getDoranQGroup());
		doranQDto.setDoranQDepth(parentDto.getDoranQDepth()+1);
		
		doranQDao.adminInsert(doranQDto);
		attr.addAttribute("doranQNo",doranQNo);
		
		return "redirect:detail";
	}
	
	@GetMapping("doran-q/list")
	public String doranQList(Model model, 
			@ModelAttribute(name="doranQListSearchVo") 
			DoranQListSearchVO vo) {
		int count = doranQDao.listCount(vo);
		vo.setCount(count);
		model.addAttribute("list",doranQDao.selectList(vo));
		return "doranq/adminList";
	}
	
	@GetMapping("doran-q/detail")
	public String doranQDetail(@RequestParam int doranQNo, Model model) {
		model.addAttribute("doranQDto", doranQDao.selectOne(doranQNo));
		return "doranq/adminDetail";
	}
	
	@GetMapping("doran-q/delete")
	public String doranQDelete(@RequestParam int doranQNo) {
		boolean result = doranQDao.delete(doranQNo);
	      if(result) {//삭제 성공
	         return "redirect:list";
	      }
	      else {//구문은 실행되었지만 바뀐 게 없는 경우 (강제 예외 처리)
	         throw new TargetNotFoundException();
	      }
		
	}
}
