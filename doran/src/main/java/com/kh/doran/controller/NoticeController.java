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
import com.kh.doran.entity.FilesDto;
import com.kh.doran.entity.NoticeDto;
import com.kh.doran.error.TargetNotFoundException;
import com.kh.doran.repository.FilesDao;
import com.kh.doran.repository.NoticeDao;
import com.kh.doran.vo.NoticeListSearchVO;


@Controller
@RequestMapping("${pageContext.request.contextPath}/notice")
public class NoticeController {

	@Autowired
	private NoticeDao noticeDao;
  
	@Autowired
	private FilesDao filesDao; 
	
	private final File directory = new File(System.getProperty("user.home"), "doranupload");
	
	@PostConstruct //최초 실행시 딱 한 번만 실행되는 메소드
	public void prepare() {
		directory.mkdirs();
	}

//	참고 : ModelAttribute로 수신한 데이터는 자동으로 Model에 첨부된다
//	- 옵션에 name을 작성하면 해당하는 이름으로 model에 첨부
	@RequestMapping("${pageContext.request.contextPath}/list")
	public String list(Model model,
			@ModelAttribute(name="vo") NoticeListSearchVO vo) {
		//페이지 네비게이터를 위한 게시글 수를 구한다
		int count = noticeDao.count(vo);
		vo.setCount(count);

		model.addAttribute("list", noticeDao.selectList(vo));
		return "notice/list";
	}

	@GetMapping("${pageContext.request.contextPath}/detail")
	public String detail(
		@RequestParam int noticeNo, Model model, HttpSession session) {
		model.addAttribute("noticeDto", noticeDao.selectOne(noticeNo));

		//(+) 추가 게시글에 대한 첨부파일을 조회하여 첨부
		model.addAttribute("filesList", 
				filesDao.selectNoticeFileList(noticeNo));
		return "notice/detail";
	}
	
}