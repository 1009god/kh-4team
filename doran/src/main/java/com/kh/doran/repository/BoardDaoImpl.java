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
import com.kh.doran.vo.BoardDetailVO;
import com.kh.doran.vo.BoardListSearchVO;
import com.kh.doran.vo.BoardListVO;

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
		
		private RowMapper<BoardListVO> listMapper = new RowMapper<BoardListVO>() {
			@Override
			public BoardListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				return BoardListVO.builder()
										.boardPostNo(rs.getInt("board_post_no"))
										.boardMemNo(rs.getInt("board_mem_no"))
										.boardTitle(rs.getString("board_title"))
										.boardWriteTime(rs.getDate("board_writetime"))
										.boardViewCnt(rs.getInt("board_view_cnt"))
										.boardReplyCnt(rs.getInt("board_reply_cnt"))
										.replyCount(rs.getInt("reply_count"))
										.memNick(rs.getString("mem_nick"))
									.build();
			}
		};
		

		
		
		@Override
		public List<BoardDto> selectList() {
			String sql = "select * from board order by board_post_no desc";
			return jdbcTemplate.query(sql, mapper);
		}

		@Override
		public List<BoardListVO> selectList(BoardListSearchVO vo) {
			if(vo.isSearch()) {//검색이라면
				return search(vo);
			}
			else {//목록이라면
				return list(vo);
			}
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
	
	private ResultSetExtractor<BoardDetailVO> detailExtractor = new ResultSetExtractor<BoardDetailVO>() {
		
		@Override
		public BoardDetailVO extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				return BoardDetailVO.builder()
						.boardPostNo(rs.getInt("board_post_no"))
						.boardMemNo(rs.getInt("board_mem_no"))
						.boardTitle(rs.getString("board_title"))
						.boardContent(rs.getString("board_content"))
						.boardWriteTime(rs.getDate("board_writetime"))
						.boardViewCnt(rs.getInt("board_view_cnt"))
						.boardReplyCnt(rs.getInt("board_reply_cnt"))
						.memNick(rs.getString("mem_nick"))
						.build();
			}
			else {
				return null;
			}
		}
	};

	@Override
	public BoardDetailVO selectOne(int boardPostNo) {
		String sql = "select "
						+ "board.*, mem_nick "
							+ "from board left outer join mem on board_mem_no = mem_no where board_post_no = ?";
		Object[] param = {boardPostNo};
		return jdbcTemplate.query(sql,  detailExtractor, param);
	}
	
	@Override
	public boolean updateReadcount(int boardPostNo) {
		String sql = "update board set board_view_cnt = "
				+ "board_view_cnt + 1 "
				+ "where board_post_no = ?";
		Object[] param = {boardPostNo};
		return jdbcTemplate.update(sql, param) > 0;
	}
	
	@Override
	public BoardDetailVO read(int boardPostNo) {
		this.updateReadcount(boardPostNo);
		return this.selectOne(boardPostNo);
	}
	
	@Override
	public int insert2(BoardDto boardDto) {
		//번호를 미리 생성한 뒤 등록하는 기능
		String sql = "select board_seq.nextval from dual";
		int boardPostNo = jdbcTemplate.queryForObject(sql, int.class);
		
		//등록 시퀀스 생성 xx
		sql = "insert into board(board_post_no, board_mem_no, "
				+ "board_title, board_content) "
				+ "values(?, ?, ?, ?)";
		Object[] param = {
				boardPostNo, boardDto.getBoardMemNo(),
				boardDto.getBoardTitle(), boardDto.getBoardContent()
		};
		jdbcTemplate.update(sql, param);
		
		return boardPostNo;
	}
	
	@Override
	public boolean delete(int boardPostNo) {
		String sql = "delete board where board_post_no = ?";
		Object[] param = {boardPostNo};
		return jdbcTemplate.update(sql,param) > 0;
	}
	
	@Override
	public boolean update(BoardDto boardDto) {
		String sql = "update board set board_title=?, board_content=? where board_post_no=?";
		Object[] param = {
			boardDto.getBoardTitle(), boardDto.getBoardContent(), boardDto.getBoardPostNo()
		};
		
	return jdbcTemplate.update(sql, param) > 0;
	}
	
	@Override
	public List<BoardListVO> search(BoardListSearchVO vo) {
		String sql = "select * from ("
				+ "select rownum rn, TMP.* from (select board_post_no, mem_nick, reply_count "
				+ "from board B "
				+ "left outer join mem M on B.board_mem_no = M.mem_no left outer join "
				+ "(select distinct reply_board_post_no, count(R.reply_no) "
				+ "over(partition by reply_board_post_no) reply_count "
				+ "from reply R) on board_post_no = reply_board_post_no) "
				+ "where instr(#1, ?) > 0 order by board_post_no desc) TMP where rn between ? and ?";
		sql = sql.replace("#1", vo.getType());
		Object[] param = {
			vo.getKeyword(), vo.startRow(), vo.endRow()
		};
		return jdbcTemplate.query(sql, listMapper, param);
	}
	
	@Override
	public List<BoardListVO> list(BoardListSearchVO vo) {
		String sql = "select * from ( "
							+ "select rownum rn, TMP.* from ( "
							+ "select B.*, mem_nick, reply_count "
									+ "from board B "
									+ "left outer join mem M on B.board_mem_no = M.mem_no left outer join "
									+ "(select distinct reply_board_post_no, count(R.reply_no) "
									+ "over(partition by reply_board_post_no) reply_count "
									+ "from reply R) on board_post_no = reply_board_post_no "
								+ "order by board_post_no desc "
							+ ")TMP "
						+ ") where rn between ? and ?";
		Object[] param = {vo.startRow(), vo.endRow()};
		return jdbcTemplate.query(sql, listMapper, param);
	}
	
	@Override
	public int count(BoardListSearchVO vo) {
		if(vo.isSearch()) {//검색이라면
			return searchCount(vo); //검색 카운트 구하는 메소드
		}
		else {
			return listCount(vo);
		}
	}
	
	@Override
	public int listCount(BoardListSearchVO vo) {
		String sql = "select count(*) from board";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	@Override
	public int searchCount(BoardListSearchVO vo) {
		String sql = "select count(*) from board where instr(#1, ?) > 0";
		sql = sql.replace("#1", vo.getType());
		Object[] param = {vo.getKeyword()};
		return jdbcTemplate.queryForObject(sql, int.class, param);
	}
}

