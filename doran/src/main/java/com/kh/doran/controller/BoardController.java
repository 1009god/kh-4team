package com.kh.doran.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.kh.doran.entity.BoardDto;
import com.kh.doran.entity.FilesDto;
import com.kh.doran.entity.ReplyDto;
import com.kh.doran.error.TargetNotFoundException;
import com.kh.doran.repository.BoardDao;
import com.kh.doran.repository.FilesDao;
import com.kh.doran.repository.ReplyDao;
import com.kh.doran.vo.BoardDetailVO;
import com.kh.doran.vo.BoardListSearchVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private ReplyDao replyDao;
	
	@Autowired
	private FilesDao filesDao; 
	
	private final File directory = new File(System.getProperty("user.home"), "doranupload");
	
	@PostConstruct //최초 실행시 딱 한 번만 실행되는 메소드
	public void prepare() {
		directory.mkdirs();
	}
	
	//modelAttribute 로 수신한 데이터는 자동으로 model 에 첨부됨
	//- 옵션에 name 을 작성하면 해당하는 이름으로 model 에 첨부
	@RequestMapping("/list")
	public String list(Model model,
			@ModelAttribute(name="vo") BoardListSearchVO vo) {
		//페이지 네비게이터를 위한 게시글 수를 구한다
		int count = boardDao.count(vo);
		vo.setCount(count);
		
		model.addAttribute("list", boardDao.selectList(vo));
		return "board/list";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam int boardPostNo, Model model,
			HttpSession session) {
//		1. 조회수를 증가시켜서 데이터를 불러온다
//		boardDao.updateReadcount(boardPostNo); //조회수 증가
		
		//2. 데이터를 읽도록 처리한다
		
//		(+ 추가) 조회수 중복 방지 처리
//		(1) 세션에 내가 읽은 게시글의 번호를 저장할 수 있는 저장소를 구현
//		-> 후보 : int[], List<Integer>, Set<Integer>
//		리스트를 선택 = 내가 읽은 게시글의 순서, 셋 순서 상관 없이 게시글을 읽은 적이 있나? (중복확인)
//		-> 세션에 저장할 이름 history로 지정
//		(2) 현재 history 가 있을지 없을지 모르므로  꺼내서 없으면 생성
		
		@SuppressWarnings("unchecked")
		Set<Integer> history = (Set<Integer>)session.getAttribute("history");
		if(history == null) {//history 가 없다면 신규 생성
			history = new HashSet<>();
		}
		
//		(3) 현재 글 번호를 읽은 적이 있는지 검사
		if(history.add(boardPostNo)) { //추가된 경우
			model.addAttribute("boardDto", boardDao.read(boardPostNo)); 
		}
		
		else {//추가가 안 된 경우 - 읽은 적이 있는 번호면
			model.addAttribute("boardDto", boardDao.selectOne(boardPostNo)); //불러와
		}
		
		System.out.println("history" + history);
		
//		(4) 갱신된 저장소를 세션에 다시 저장
		session.setAttribute("history", history);
		
//		(+ 추가) 댓글 목록을 조회하여 첨부
		model.addAttribute("replyList",replyDao.selectList(boardPostNo));
		
		model.addAttribute("filesList", 
				filesDao.selectBoardFileList(boardPostNo));
		return "board/detail";
	}
	
	@GetMapping("/write")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/write") 
	public String write(
			@ModelAttribute BoardDto boardDto,
			@RequestParam List<MultipartFile> files,
			HttpSession session, RedirectAttributes attr) throws IllegalStateException, IOException{
		//session 에 있는 회원 번호를 작성자로 추가한 뒤 등록해야 함
		int memNo = (int)session.getAttribute("loginNo");
		boardDto.setBoardMemNo(memNo);
		
		//boardDao.insert(boardDto);
		//return "redirect:list";
		
		//문제점 : 등록은 되는데 몇 번인지 알 수 없다
		//해결책 : 번호를 미리 생성하고 등록하도록 메소드 변경
		int boardPostNo = boardDao.insert2(boardDto);
		
		//(+ 추가) 게시글이 등록된 다음 파일이 있다면 해당 파일을 등록 및 연결
				//- 첨부파일이 없어도 리스트에는 1개의 객체가 들어있다
				for(MultipartFile file : files) {
					if(!file.isEmpty()) {
						System.out.println("첨부파일 발견");
						
						int filesNo = filesDao.sequence();
						filesDao.insert(FilesDto.builder()
								.filesNo(filesNo)
								.filesUploadname(file.getOriginalFilename())
								.filesType(file.getContentType())
								.filesSize(file.getSize())
								.build());
						//파일저장
						File target = new File(directory,String.valueOf(filesNo));
						System.out.println(target.getAbsolutePath());
						file.transferTo(target);
						
						//+ 연결 테이블에 연결 정보를 저장 (게시글 번호, 첨부파일 번호)
						boardDao.connectFiles(boardPostNo, filesNo);
					}
				}
		
		attr.addAttribute("boardPostNo", boardPostNo);
		return "redirect:detail";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam int boardPostNo) {
		boolean result = boardDao.delete(boardPostNo);
	      if(result) {//삭제 성공
	         return "redirect:list";
	      }
	      else {//구문은 실행되었지만 바뀐 게 없는 경우 (강제 예외 처리)
	         throw new TargetNotFoundException();
	      }
		
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam int boardPostNo, Model model) {
		BoardDetailVO boardDto = boardDao.selectOne(boardPostNo);
		if(boardDto == null) { //없는 경우 내가 만든 예외 발생
			throw new TargetNotFoundException();
		}
		model.addAttribute("boardDto", boardDto);
		return "board/edit";
	}
	
	@PostMapping("/edit")
	public String edit(@ModelAttribute BoardDto boardDto,
			RedirectAttributes attr) {
		boolean result = boardDao.update(boardDto);
		if(result) {//성공했으면 상세페이지
			attr.addAttribute("boardPostNo", boardDto.getBoardPostNo());
			return "redirect:detail";
		}
		
		else { //실패했으면 오류 발생
			throw new TargetNotFoundException();
		}
	}
	
	@PostMapping("/reply/write")
	public String replyWrite(
			@ModelAttribute ReplyDto replyDto,
			RedirectAttributes attr, HttpSession session) {
		int memNo = (int)session.getAttribute("loginNo");
		replyDto.setReplyMemNo(memNo);
		replyDao.insert(replyDto);
		
		attr.addAttribute("boardPostNo", replyDto.getReplyBoardPostNo());
		return "redirect:/board/detail"; //절대
	}
	
	@GetMapping("/reply/delete")
	public String replyDelete(
			@RequestParam int replyNo,
			@RequestParam int replyBoardPostNo,
			RedirectAttributes attr) {
		replyDao.delete(replyNo);
		attr.addAttribute("boardPostNo", replyBoardPostNo);
		return "redirect:/board/detail";
	}
	
	@PostMapping("/reply/edit")
	public String replyEdit(
			@ModelAttribute ReplyDto replyDto,
			RedirectAttributes attr) {
		replyDao.update(replyDto);
		attr.addAttribute("boardPostNo", replyDto.getReplyBoardPostNo());
		return "redirect:/board/detail";
		
	}
}
		

