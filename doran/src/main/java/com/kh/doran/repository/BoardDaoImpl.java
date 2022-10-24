package com.kh.doran.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.kh.doran.entity.BoardDto;


public class BoardDaoImpl implements BoardDao{
	private static RowMapper<BoardDto> mapper = new RowMapper<BoardDto>() {
		
		@Override
		public BoardDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardDto dto = new BoardDto();
			dto.setBoardPostNo(rs.getInt("board_post_no"));
			dto.setBoardMemNo(rs.getInt("board_mem_no"));
			dto.setBoardTitle(rs.getString("board_title"));
			dto.setBoardContent(rs.getString("board_content"));
			dto.setBoardWriteTime(rs.getDate("board_writetime"));
			dto.setBoardViewCnt(rs.getInt("board_view_cnt"));
			dto.setBoardReplyCnt(rs.getInt("board_reply_cnt"));
			return dto;
		}
	};
	
	private static ResultSetExtractor<BoardDto> extractor = new ResultSetExtractor<BoardDto>() {
		
		@Override
		public BoardDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setBoardPostNo(rs.getInt("board_post_no"));
				dto.setBoardMemNo(rs.getInt("board_mem_no"));
				dto.setBoardTitle(rs.getString("board_title"));
				dto.setBoardContent(rs.getString("board_content"));
				dto.setBoardWriteTime(rs.getDate("board_writetime"));
				dto.setBoardViewCnt(rs.getInt("board_view_cnt"));
				dto.setBoardReplyCnt(rs.getInt("board_reply_cnt"));
				return dto;
			}
			else {
				return null;
			}
			
		}
	};
	
	public static RowMapper<BoardDto> getMapper() {
		return mapper;
	}
	
	public static ResultSetExtractor<BoardDto> getExtractor() {
		return extractor;
	}

	


}