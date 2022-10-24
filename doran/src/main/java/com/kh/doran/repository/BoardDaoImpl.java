package com.kh.doran.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.doran.entity.BoardDto;
import com.kh.doran.vo.BoardListSearchVO;

@Repository
public class BoardDaoImpl implements BoardDao{
		
		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		@Override
		public void insert(BoardDto boardDto) {
			String sql = "insert into board(board_post_no, board_mem_no, "
							+ "board_title, "
							+ "board_content) "
							+ "values(board_seq.nextval, ?, ?, ?)";
			Object[] param = {boardDto.getBoardMemNo(),
										boardDto.getBoardTitle(),
										boardDto.getBoardContent()};
			jdbcTemplate.update(sql, param);
		}
		
		@Override
		public void clear() {
			String sql = "delete board";
			jdbcTemplate.update(sql);
		}
		
		
		private RowMapper<BoardDto> mapper = new RowMapper<BoardDto>() {
			@Override
			public BoardDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				return BoardDto.builder()
										.boardPostNo(rs.getInt("board_post_no"))
										.boardMemNo(rs.getInt("board_mem_no"))
										.boardTitle(rs.getString("board_title"))
										.boardContent(rs.getString("board_content"))
										.boardWriteTime(rs.getDate("board_writetime"))
										.boardViewCnt(rs.getInt("board_view_cnt"))
										.boardReplyCnt(rs.getInt("board_reply_cnt"))
									.build();
			}
		};
		
		@Override
		public List<BoardDto> selectList() {
			String sql = "select * from board order by board_post_no desc";
			return jdbcTemplate.query(sql, mapper);
		}
		
		@Override
		public List<BoardDto> selectList(BoardListSearchVO vo) {
			String sql = "select * from board "
					+ "where instr(#1, ?) > 0 "
					+ "order by board_post_no desc";
		sql = sql.replace("#1", vo.getType());
		Object[] param = {vo.getKeyword()};
		return jdbcTemplate.query(sql, mapper, param);
	}

		
	private ResultSetExtractor<BoardDto> extractor = new ResultSetExtractor<BoardDto>() {
		
		@Override
		public BoardDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				return BoardDto.builder()
						.boardPostNo(rs.getInt("board_post_no"))
						.boardMemNo(rs.getInt("board_mem_no"))
						.boardTitle(rs.getString("board_title"))
						.boardContent(rs.getString("board_content"))
						.boardWriteTime(rs.getDate("board_writetime"))
						.boardViewCnt(rs.getInt("board_view_cnt"))
						.boardReplyCnt(rs.getInt("board_reply_cnt"))
					.build();
			}
			else {
				return null;
			}
		}
	};

	@Override
	public BoardDto selectOne(int boardPostNo) {
		String sql = "select * from board where board_post_no = ?";
		Object[] param = {boardPostNo};
		return jdbcTemplate.query(sql,  extractor, param);
	}
	
}

