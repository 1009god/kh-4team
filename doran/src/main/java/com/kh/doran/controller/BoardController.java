package com.kh.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.doran.entity.BoardDto;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(@ModelAttribute BoardDto dto) {
		String sql = "insert into board(board_post_no, board_mem_no, board_title, "
				+ "board_content, board_writetime, board_view_cnt, board_reply_cnt) "
				+ "values(board_seq.nextval, ?, ?, ?, sysdate, ?, ?)";
		Object[] param = {dto.getBoardMemNo(), dto.getBoardTitle(), dto.getBoardContent(), dto.getBoardViewCnt(), dto.getBoardReplyCnt()};
		jdbcTemplate.update(sql, param);
		return "등록 완료!";
	}
	
//	@RequestMapping("/list")
//	@RequestMapping("/search")
//	@RequestMapping("/detail")
	@RequestMapping("/update")
	@ResponseBody
	public String update(@ModelAttribute BoardDto dto) {
		String sql = "update board set board_title=?, board_content=? where board_post_no=?";
		Object[] param = {dto.getBoardTitle(), dto.getBoardContent(), dto.getBoardPostNo()};
		int result = jdbcTemplate.update(sql, param);
		if(result > 0) {
			return "수정 성공";
		}
		else {
			return "존재하지 않는 번호";
		}
	}
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(@RequestParam int boardPostNo) {
		String sql = "delete board where board_post_no=?";
		Object[] param = {boardPostNo};
		int result = jdbcTemplate.update(sql, param);
		if(result > 0) {
			return "삭제 완료";
		}
		else {
			return "존재하지 않는 번호";
		}

}

}
