package com.kh.doran.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.doran.entity.AddressDto;
import com.kh.doran.entity.FilesDto;
import com.kh.doran.entity.MemDto;
import com.kh.doran.repository.AddressDao;
import com.kh.doran.repository.FilesDao;
import com.kh.doran.repository.MemDao;

@Controller
@RequestMapping("/edit")
public class EditController {

	@Autowired
	private MemDao memDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private FilesDao filesDao;
	
	private final File directory = new File(System.getProperty("user.home"), "doranupload");
	
	@PostConstruct //최소 실행시 딱 한 번만 실행되는 메소드
	public void prepare() {
		directory.mkdirs();
	}
	
	
	//프로필 정보 수정
		// 1. 자신의 현재 정보를 조회하여 화면에 출력
		// 2. 바꾸고 싶은 정보를 입력하여 전송하면 해당 정보를 변경

		@GetMapping("/profile")
		public String editProfile(HttpSession session, Model model) {
			
			//(1)아이디 획득(HttpSession)
			int memNo = (int)session.getAttribute("loginNo");
			
			//(2) 아이디로 정보를 조회
			MemDto memDto = memDao.selectOne(memNo);
			
			//(3) 조회한 정보를 화면으로 전달
			model.addAttribute("memDto",memDto);
						
			 //(+추가) 프로필 이미지
		     model.addAttribute("profileImg", filesDao.profileImgList(memNo));
			
			//(4) 연결될 화면 주소를 반환
			return "edit/profile";
		}
		
		@PostMapping("/profile")
		public String editProfile(HttpSession session, 
												@ModelAttribute MemDto inputDto, //client가 입력한 값
												@RequestParam List<MultipartFile> files,
												RedirectAttributes attr) throws IllegalStateException, IOException {
			// memberNo는 input으로 받는것이 없음-> session에서 꺼내온다 -> 추가 설정을 해야함
			int memNo = (int)session.getAttribute("loginNo");
			
			
			
			boolean result = memDao.editProfile(inputDto);  //프로필 내용 수정
			
			//수정입력이 된 다음
			//프로필 이미지 파일이 있다면 등록 및 연결
			
			//프로필이미지 파라미터가 null이라면 첨부파일이 없는것
			
			if(result) {					
				for(MultipartFile file : files) {
					boolean imgResult = file.isEmpty(); //true면 첨부파일이 없는것
					if(!imgResult) { //true가 아니라면
						System.out.println("첨부파일이 존재합니다");
						
						inputDto.setMemNo(memNo); //memberDto에 세션에서 가져온 memNo를 넣어줌  // 지금 사용자의 no
						
						//db등록
						int filesNo = filesDao.sequence();
						filesDao.insert(FilesDto.builder()
								.filesNo(filesNo)
								.filesUploadname(file.getOriginalFilename())
								.filesType(file.getContentType())
								.filesSize(file.getSize())
								.build());
						
						//파일저장
						File target = new File(directory,String.valueOf(filesNo));
						file.transferTo(target);
						
						//연결테이블에 연결정보를 저장(회원번호, 첨부파일번호)
						filesDao.connectFiles(filesNo, memNo);
					}					
					
				}				
				attr.addAttribute("memNo",inputDto.getMemNo());	
				return "redirect:/mypage/profile";	
			}		
			else {
				return "redirect:/edit/editFail";			
			}	
		}
			

		@GetMapping("/profile_result")
		public String profileResult() {
			return "/mypage/profile";
		}		
		
//account 수정 전 정보확인 페이지 
		@GetMapping("/account")
		public String account(Model model, HttpSession session) {
			//(1)아이디 획득(HttpSession)
			int memNo = (int)session.getAttribute("loginNo");			
			//(2) 아이디로 정보를 조회
			MemDto memDto = memDao.selectOne(memNo);			
			//(3) 조회한 정보를 화면으로 전달
			model.addAttribute("memDto",memDto);			
			//(4) 연결될 화면 주소를 반환
			return "edit/account";
		}
		
		
//account 정보 수정
	// 비밀번호 수정
		
		@GetMapping("/account_change_pw")
		public String editAccountChangePw(HttpSession session, Model model) {
			
			//(1)아이디 획득(HttpSession)
			int memNo = (int)session.getAttribute("loginNo");			
			//(2) 아이디로 정보를 조회
			MemDto memDto = memDao.selectOne(memNo);			
			//(3) 조회한 정보를 화면으로 전달
			model.addAttribute("memDto",memDto);			
			//(4) 연결될 화면 주소를 반환
			return "edit/accountChangePw";
		}
		
		@PostMapping("/account_change_pw")
		public String editAccountChangePw(HttpSession session, 
												@ModelAttribute MemDto inputDto, //client가 입력한 값
												RedirectAttributes attr, Model model) {
			// memberNo는 input으로 받는것이 없음-> session에서 꺼내온다 -> 추가 설정을 해야함
			int memNo = (int)session.getAttribute("loginNo");
			inputDto.setMemNo(memNo); //memberDto에 세션에서 가져온 memNo를 넣어줌  // 지금 사용자의 no
			
			model.addAttribute("findPw", memDao.findPw(memNo)); // 해당 회원의 비밀번호 찾아옴
			
			boolean result = memDao.editAccountPw(inputDto);
			
			if(result) {			
				attr.addAttribute("memNo",inputDto.getMemNo());	
				return "redirect:/edit/account_result";	
			}		
			else {
				return "redirect:/edit/editFail";
			}	
		}

		@GetMapping("/account_change_pw_result")
		public String editAccountChangePwResult() {
			return "edit/account";
		}
		
		
		
		
		
//account		
// 전화번호 수정			
//		@GetMapping("/account_change_tel")
//		public String editAccount(HttpSession session, Model model) {
//			
//			//(1)아이디 획득(HttpSession)
//			int memNo = (int)session.getAttribute("loginNo");			
//			//(2) 아이디로 정보를 조회
//			MemDto memDto = memDao.selectOne(memNo);			
//			//(3) 조회한 정보를 화면으로 전달
//			model.addAttribute("memDto",memDto);			
//			//(4) 연결될 화면 주소를 반환
//			return "edit/accountChangeTel";
//		}
		
		@PostMapping("/account")
		public String editAccount(HttpSession session, 
												@ModelAttribute MemDto inputDto, //client가 입력한 값
												RedirectAttributes attr) {
			// memberNo는 input으로 받는것이 없음-> session에서 꺼내온다 -> 추가 설정을 해야함
			int memNo = (int)session.getAttribute("loginNo");
			inputDto.setMemNo(memNo); //memberDto에 세션에서 가져온 memNo를 넣어줌  // 지금 사용자의 no
			
			boolean result = memDao.editAccountTel(inputDto);
			
			if(result) {			
				attr.addAttribute("memNo",inputDto.getMemNo());	
				return "redirect:/edit/account_result";	
			}		
			else {
				return "redirect:/edit/editFail";
			}	
		}

		@GetMapping("/account_result")
		public String accountResult() {
			return "redirect:/edit/account";
		}
		
				
		
		
		
		
		//배송지 수정-배송지 추가
		@GetMapping("/address_plus")
		public String address() {
			return "edit/addressPlus";
		}
		
		@PostMapping("/address_plus")
		public String address(
				HttpSession session,
				@ModelAttribute AddressDto inputDto) {		
			addressDao.insert(inputDto);
			return "redirect:address_finish";
		}
		
		@GetMapping("/address_finish")
		public String addressFinish() {
			return "redirect:/edit/address_list";
		}
		
		//배송지 리스트
		@GetMapping("/address_list")
		public String addressList(Model model) {
			List<AddressDto> list = addressDao.selectList();
			model.addAttribute("list",list);
			return "edit/addressList";
		}

		//배송지 삭제
		@GetMapping("/address_delete")
		public String addressDelete(@RequestParam int addressNo) {
			boolean result = addressDao.delete(addressNo);
			if(result) {
				return "redirect:/edit/address_list";
			}
			else {
				return "edit/editFail";
			}
		}
		
		//배송지 수정
		@GetMapping("/address_change")
		public String addressChange(Model model, @RequestParam int addressNo) {
			AddressDto dto = addressDao.selectOne(addressNo);
			model.addAttribute("addressDto", dto);
			return "edit/addressChange";
		}
		
		
		@PostMapping("/address_change")
		public String addressChange(@ModelAttribute AddressDto addressDto, RedirectAttributes attr) {
			boolean result = addressDao.update(addressDto);
			if(result) {
				attr.addAttribute("addressNo", addressDto.getAddressNo());
				return "redirect:/edit/address_list";
			}
			else {
				return "redirect:edit_fail";
			}
		}
		
		@GetMapping("/edit_fail")
		public String editFail() {
			return "edit/editFail";
		}

		
}
