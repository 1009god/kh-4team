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

import com.kh.doran.entity.MemDto;
import com.kh.doran.repository.FilesDao;
import com.kh.doran.repository.MemDao;
import com.kh.doran.repository.OrdersDao;
import com.kh.doran.vo.OrdersMemNoSearchVO;




@Controller
@RequestMapping("/mypage")
public class MemMypageController {
	
	@Autowired
	private MemDao memDao;
	
	@Autowired
	private FilesDao filesDao;
	
	@Autowired
	private OrdersDao ordersDao;
	
	//프로필 홈
  @GetMapping("/profile")
  public String mypage(HttpSession session, Model model) {
     //1. 세션에 들어있는 아이디를 꺼낸다 (down casting다운캐스팅) 형변환?
     //- 세션에 저장된 형태가 Object이기 때문에 string으로 다운캐스팅
	  int memNo = (int)session.getAttribute("loginNo");     
	     
	     //2. 아이드를 이용하여 회원정보를 불러온다
	     MemDto memDto = memDao.selectOne(memNo);
	     
	     //3.불러온 정보를 모델에 첨부한다
	     model.addAttribute("memDto", memDto);
	     
	     //(+추가) 프로필 이미지
	     model.addAttribute("profileImg", filesDao.profileImgList(memNo));
	     
	     //4.화면(view)으로 전달(forward)한다	     
	     return "mypage/profile";
  }
  
//회원 탈퇴!!	

	@GetMapping("/goodbye_content") //회원 탈퇴 전 중요 내용
	public String goodbyeContent() {
		return "mypage/goodbyeContent";
	}
	
	@GetMapping("/goodbye") //회원
	public String goodbye() {
		return "mypage/goodbye";
	}
	
	@PostMapping("/goodbye") //회원
	public String goodbye(HttpSession session,
			@RequestParam String memPw) {
		String memEmail = (String)session.getAttribute("loginId");
		MemDto memDto = memDao.selectOne(memEmail);
		boolean passwordMatch =
				memPw.equals(memDto.getMemPw());
		if(passwordMatch) {
			//회원 탈퇴
			memDao.delete(memEmail);
			//로그아웃
			session.removeAttribute("loginId");
			session.removeAttribute("loginNo");
			
			return "redirect:goodbye_result";
		}
		else {
			return "redirect:goodbye?error";
		}
	}
	
	@GetMapping("/goodbye_result") //비회원 나중에 인터셉터로
	public String goodbyeResult() {
		return "mypage/goodbyeResult";
	}
	
//올린 프로젝트 created
	@GetMapping("/created")
	public String created() {
		return "mypage/created";
	}
	
	
//후원한 프로젝트 supported
	@GetMapping("/supported")
	public String supported(HttpSession session, Model model) {
		int memNo = (int)session.getAttribute("loginNo");
		 //2. 아이드를 이용하여 회원정보를 불러온다
	     MemDto memDto = memDao.selectOne(memNo);
	     
	     //3.불러온 정보를 모델에 첨부한다
	     model.addAttribute("memDto", memDto);
	     
	     //(+추가) 프로필 이미지
	     model.addAttribute("profileImg", filesDao.profileImgList(memNo));
	     
	     //내가 후원한 목록
	     
	     
	     
	     //(+추가) 후원한 목록- 아마 모델에 첨부해서 프론트에서 배열 돌릴것으로 예상

	     model.addAttribute("OrdersMemSearchDto", ordersDao.memNoSearch(memNo));

		return "mypage/supported";
	}
	


	
	
	
}
