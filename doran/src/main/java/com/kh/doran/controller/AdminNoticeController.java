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

import com.kh.doran.entity.FilesDto;
import com.kh.doran.entity.NoticeDto;
import com.kh.doran.error.TargetNotFoundException;
import com.kh.doran.repository.FilesDao;
import com.kh.doran.repository.NoticeDao;
import com.kh.doran.service.NoticeService;
import com.kh.doran.vo.NoticeListSearchVO;


@Controller
@RequestMapping("/admin")
public class AdminNoticeController {
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private FilesDao filesDao; 
	
	private final File directory = new File(System.getProperty("user.home"), "doranupload");
	
	@Autowired
	private NoticeService noticeService;
	
	@PostConstruct //최초 실행시 딱 한 번만 실행되는 메소드
	public void prepare() {
		directory.mkdirs();
	}
	
//	참고 : ModelAttribute로 수신한 데이터는 자동으로 Model에 첨부된다
//	- 옵션에 name을 작성하면 해당하는 이름으로 model에 첨부
	@RequestMapping("/noticelist")
	public String list(Model model,HttpSession session,
			@ModelAttribute(name="vo") NoticeListSearchVO vo) {
		//페이지 네비게이터를 위한 게시글 수를 구한다
		int count = noticeDao.count(vo);
		vo.setCount(count);
		
		model.addAttribute("list", noticeDao.selectList(vo));
		if(session.getAttribute("AdminNo")!=null) {
			return "admin/noticelist";			
		}
		else {
			return "admin/login";
		}
	}
	
	@GetMapping("/noticedetail")
	public String detail(
		@RequestParam int noticeNo, Model model, HttpSession session) {
		model.addAttribute("noticeDto", noticeDao.selectOne(noticeNo));
		
		//(+) 추가 게시글에 대한 첨부파일을 조회하여 첨부
		model.addAttribute("filesList", 
				filesDao.selectNoticeFileList(noticeNo));
		return "admin/noticedetail";
	}
	
	@GetMapping("/noticewrite")
	public String write() {
		return "admin/noticewrite";
	}
	
	@PostMapping("/noticewrite") 
	public String write(
			@ModelAttribute NoticeDto noticeDto,
			@RequestParam List<MultipartFile> files,
			HttpSession session, RedirectAttributes attr) throws IllegalStateException, IOException {
		//session 에 있는 회원 번호를 작성자로 추가한 뒤 등록해야 함
		int adminNo = (int)session.getAttribute("AdminNo");
		noticeDto.setNoticeAdminNo(adminNo);
		
		int noticeNo = noticeService.write(noticeDto, files);
		
		attr.addAttribute("noticeNo", noticeNo);
		return "redirect:noticedetail";
	}
	
	@GetMapping("/noticedelete")
	public String delete(@RequestParam int noticeNo) {
		boolean result = noticeService.remove(noticeNo);
		if(result) {//성공
			return "redirect:noticelist";
		}
		else {//구문은 실행되었지만 바뀐 게 없는 경우(강제 예외 처리)
			throw new TargetNotFoundException();
		}
	}
	
	@GetMapping("/noticeedit")
	public String edit(@RequestParam int noticeNo, Model model) {
		NoticeDto noticeDto = noticeDao.selectOne(noticeNo);
		if(noticeDto == null) {//없는 경우 내가 만든 예외 발생
			throw new TargetNotFoundException();
		}
		model.addAttribute("noticeDto", noticeDto);
		return "admin/noticeedit";
	}
	
	@PostMapping("/noticeedit")
	public String edit(@ModelAttribute NoticeDto noticeDto,
			RedirectAttributes attr) {
		boolean result = noticeDao.update(noticeDto);
		if(result) {//성공했다면 상세페이지로 이동
			attr.addAttribute("noticeNo", noticeDto.getNoticeNo());
			return "redirect:noticedetail";
		}
		else {//실패했다면 오류 발생
			throw new TargetNotFoundException();
		}
	}
	
}
