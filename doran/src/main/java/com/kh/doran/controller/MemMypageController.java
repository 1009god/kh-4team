package com.kh.doran.controller;

import java.util.List;

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
import com.kh.doran.entity.OrdersDto;
import com.kh.doran.entity.PjDto;
import com.kh.doran.repository.AddressDao;
import com.kh.doran.repository.FilesDao;
import com.kh.doran.repository.MemDao;
import com.kh.doran.repository.OrdersDao;
import com.kh.doran.repository.PjDao;
import com.kh.doran.vo.CreatedDetailVO;




@Controller
@RequestMapping("/mypage")
public class MemMypageController {
	
	@Autowired
	private MemDao memDao;
	
	@Autowired
	private FilesDao filesDao;
	
	@Autowired
	private OrdersDao ordersDao;
	
	@Autowired
	private PjDao pjDao;
	
	@Autowired
	private AddressDao addressDao;
	
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
	public String created(HttpSession session, Model model) {
		int memNo = (int)session.getAttribute("loginNo");
		 //2. 아이드를 이용하여 회원정보를 불러온다
	     MemDto memDto = memDao.selectOne(memNo);
	     
	     //3.불러온 정보를 모델에 첨부한다
	     model.addAttribute("memDto", memDto);
	     
	     //(+추가) 프로필 이미지
	     model.addAttribute("profileImg", filesDao.profileImgList(memNo));
	     
	     //(+추가) 지금 접속한 유저가 생성한 모든 프로젝트
	     List<PjDto> myCreatedPjDto=pjDao.selectSeller(memDto.getMemNo());
	     model.addAttribute("myCreatedPjDto", myCreatedPjDto);
		
		return "mypage/created";
	}
	
	//내가 올린 프로젝트들 중->특정 프로젝트를 선택하면->얼마나 팔렸는지 현황
	@GetMapping("/created/detail")
	public String createdDetail(@RequestParam int pjNo, HttpSession session, Model model) {
		model.addAttribute("createdDetailDto", ordersDao.selectCreatedDetail(pjNo));
		return "mypage/createdDetail";
	};
	
	
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
	     
	     //(+추가) 후원한 목록
	     model.addAttribute("supportList", ordersDao.selectSupportList(memNo));
	     
	    
		return "mypage/supported";
	}
	
	//후원한 프로젝트 상세 supported/detail
	@GetMapping("/supported/detail")
	public String supportedDetail(@RequestParam int ordersNo, Model model,HttpSession session) {
		//세션에서 멤버 번호 저장
		int memNo = (int)session.getAttribute("loginNo");
		
		//(+추가) 후원한 프로젝트 상세
	     model.addAttribute("supportPjImfo", ordersDao.selectSupportDetail(ordersNo));
	     
	     //(+추가) 후원한 프로젝트 상세-order,option,deliver
		model.addAttribute("supportDetail", ordersDao.selectSupportDetail2(ordersNo));		
		
		//(+추가) 후원한 프로젝트의 상세 썸네일 이미지
		model.addAttribute("supportDetailImg", filesDao.supportDetailImgList(ordersNo));
		
		return "mypage/supportedDetail";
	}
	


	@GetMapping("/supported/cancel")
	public String supportedCancel(@RequestParam int ordersNo) {
		ordersDao.orderCancel(ordersNo);
		return "redirect:/mypage/supported";
	};
	
	
	
}