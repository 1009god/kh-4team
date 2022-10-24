package com.kh.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.doran.repository.BoardDao;
import com.kh.doran.vo.BoardListSearchVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardDao boardDao;
	
	@RequestMapping("/list")
	public String list(Model model,
			@ModelAttribute BoardListSearchVO vo) {
		if(vo.isSearch()) {
			model.addAttribute("list", boardDao.selectList(vo.getType(), vo.getKeyword()));
		}
		else {
			model.addAttribute("list", boardDao.selectList());
		}
//		int boardMemNo = (int)session.getAttribute("loginNo");
//		BoardDto boardDto = memDao.selectOne(memNo);
//		model.addAttribute("boardDto", boardDto);
		return "board/list";
	}
}
		

//	@RequestMapping("/search")
//	@ResponseBody
//	public String search(@RequestParam String type,
//			@RequestParam String keyword) {
//		
//		String sql = "select * from board " 
//				+ "where instr(#1, ?) > 0 order by board_post_no asc";
//		sql = sql.replace("#1", type);
//		Object[] param = {keyword};
//		List<BoardDto> data = jdbcTemplate.query(sql, BoardDto.getMapper(), param);
//		
//		StringBuffer buffer = new StringBuffer();
//		for(BoardDto dto : data) {
//			buffer.append(dto);
//			buffer.append("<br>");
//		}
//		return buffer.toString();
//		
//	}
//	
//	@RequestMapping("/detail")
//	@ResponseBody
//	public String detail(@RequestParam int boardPostNo) {
//		String sql = "select * from board where board_post_no=?";
//		Object[] param = {boardPostNo};
//		
//		BoardDto dto = jdbcTemplate.query(sql, BoardDto.getExtractor(), param);
//		if(dto == null) {
//			return "없는 번호";
//		}
//		else {
//			return dto.toString();
//		}
//	}
//	
//	
//	@RequestMapping("/update")
//	@ResponseBody
//	public String update(@ModelAttribute BoardDto dto) {
//		String sql = "update board set board_title=?, board_content=? where board_post_no=?";
//		Object[] param = {dto.getBoardTitle(), dto.getBoardContent(), dto.getBoardPostNo()};
//		int result = jdbcTemplate.update(sql, param);
//		if(result > 0) {
//			return "수정 성공";
//		}
//		else {
//			return "존재하지 않는 번호";
//		}
//	}
//	@RequestMapping("/delete")
//	@ResponseBody
//	public String delete(@RequestParam int boardPostNo) {
//		String sql = "delete board where board_post_no=?";
//		Object[] param = {boardPostNo};
//		int result = jdbcTemplate.update(sql, param);
//		if(result > 0) {
//			return "삭제 완료";
//		}
//		else {
//			return "존재하지 않는 번호";
//		}
//
//}
//
//}
