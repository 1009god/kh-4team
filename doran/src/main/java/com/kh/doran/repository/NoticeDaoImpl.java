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

import com.kh.doran.entity.NoticeDto;
import com.kh.doran.vo.NoticeListSearchVO;
import com.kh.doran.vo.NoticeListSearchVO;

@Repository
public class NoticeDaoImpl implements NoticeDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insert(NoticeDto noticeDto) {
		String sql = "insert into notice(notice_no, notice_admin_no, "
						+ "notice_title, "
						+ "notice_content) "
						+ "values(notice_seq.nextval, ?, ?, ?)";
		Object[] param = {noticeDto.getNoticeAdminNo(),
										noticeDto.getNoticeTitle(),
										noticeDto.getNoticeContent()};
		jdbcTemplate.update(sql, param);
	}
	
	@Override
	public void clear() {
		String sql = "delete notice";
		jdbcTemplate.update(sql);
	}
	
	
	
	private RowMapper<NoticeDto> mapper = new RowMapper<NoticeDto>() {
		@Override
		public NoticeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return NoticeDto.builder()
									.noticeNo(rs.getInt("notice_no"))
									.noticeAdminNo(rs.getInt("notice_admin_no"))
									.noticeTitle(rs.getString("notice_title"))
									.noticeContent(rs.getString("notice_content"))
									.noticeWriteTime(rs.getDate("notice_writetime"))
								.build();
		}
	};
//	
//	private RowMapper<BoardListVO> listMapper = new RowMapper<BoardListVO>() {
//		@Override
//		public BoardListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
//			return BoardListVO.builder()
//									.boardPostNo(rs.getInt("board_post_no"))
//									.boardMemNo(rs.getInt("board_mem_no"))
//									.boardTitle(rs.getString("board_title"))
//									.boardWriteTime(rs.getDate("board_writetime"))
//									.boardViewCnt(rs.getInt("board_view_cnt"))
//									.boardReplyCnt(rs.getInt("board_reply_cnt"))
//									.replyCount(rs.getInt("reply_count"))
//									.memNick(rs.getString("mem_nick"))
//								.build();
//		}
//	};
	

	
	
	@Override
	public List<NoticeDto> selectList() {
		String sql = "select * from notice order by notice_no desc";
		return jdbcTemplate.query(sql, mapper);
	}

	@Override
	public List<NoticeDto> selectList(NoticeListSearchVO vo) {
		String sql = "select * from notice where instr(#1, ?) > 0 order by notice_no desc";
		sql = sql.replace("#1", vo.getType());
		Object[] param = {vo.getKeyword()};
		return jdbcTemplate.query(sql, mapper, param);
	}

	
private ResultSetExtractor<NoticeDto> extractor = new ResultSetExtractor<NoticeDto>() {
	
	@Override
	public NoticeDto extractData(ResultSet rs) throws SQLException, DataAccessException {
		if(rs.next()) {
			return NoticeDto.builder()
					.noticeNo(rs.getInt("notice_no"))
					.noticeAdminNo(rs.getInt("notice_admin_no"))
					.noticeTitle(rs.getString("notice_title"))
					.noticeContent(rs.getString("notice_content"))
					.noticeWriteTime(rs.getDate("notice_writetime"))
				.build();
		}
		else {
			return null;
		}
	}
};


@Override
public NoticeDto selectOne(int noticeNo) {
	String sql = "select * from board where notice_no = ?";
	Object[] param = {noticeNo};
	return jdbcTemplate.query(sql, extractor, param);
}

//@Override
//public int insert2(BoardDto boardDto) {
//	//번호를 미리 생성한 뒤 등록하는 기능
//	String sql = "select board_seq.nextval from dual";
//	int boardPostNo = jdbcTemplate.queryForObject(sql, int.class);
//	
//	//등록 시퀀스 생성 xx
//	sql = "insert into board(board_post_no, board_mem_no, "
//			+ "board_title, board_content) "
//			+ "values(?, ?, ?, ?)";
//	Object[] param = {
//			boardPostNo, boardDto.getBoardMemNo(),
//			boardDto.getBoardTitle(), boardDto.getBoardContent()
//	};
//	jdbcTemplate.update(sql, param);
//	
//	return boardPostNo;
//}
//
//@Override
//public boolean delete(int boardPostNo) {
//	String sql = "delete board where board_post_no = ?";
//	Object[] param = {boardPostNo};
//	return jdbcTemplate.update(sql,param) > 0;
//}
//
//@Override
//public boolean update(NoticeDto noticeDto) {
//	String sql = "update board set board_title=?, board_content=? where board_post_no=?";
//	Object[] param = {
//		boardDto.getBoardTitle(), boardDto.getBoardContent(), boardDto.getBoardPostNo()
//	};
//	
//return jdbcTemplate.update(sql, param) > 0;
//}
//
//@Override
//public List<BoardListVO> search(NoticeListSearchVO vo) {
//	String sql = "select * from ("
//			+ "select rownum rn, TMP.* from (select board_post_no, mem_nick, reply_count "
//			+ "from board B "
//			+ "left outer join mem M on B.board_mem_no = M.mem_no left outer join "
//			+ "(select distinct reply_board_post_no, count(R.reply_no) "
//			+ "over(partition by reply_board_post_no) reply_count "
//			+ "from reply R) on board_post_no = reply_board_post_no) "
//			+ "where instr(#1, ?) > 0 order by board_post_no desc) TMP where rn between ? and ?";
//	sql = sql.replace("#1", vo.getType());
//	Object[] param = {
//		vo.getKeyword(), vo.startRow(), vo.endRow()
//	};
//	return jdbcTemplate.query(sql, listMapper, param);
//}
//
//@Override
//public List<BoardListVO> list(NoticeListSearchVO vo) {
//	String sql = "select * from ( "
//						+ "select rownum rn, TMP.* from ( "
//						+ "select B.*, mem_nick, reply_count "
//								+ "from board B "
//								+ "left outer join mem M on B.board_mem_no = M.mem_no left outer join "
//								+ "(select distinct reply_board_post_no, count(R.reply_no) "
//								+ "over(partition by reply_board_post_no) reply_count "
//								+ "from reply R) on board_post_no = reply_board_post_no "
//							+ "order by board_post_no desc "
//						+ ")TMP "
//					+ ") where rn between ? and ?";
//	Object[] param = {vo.startRow(), vo.endRow()};
//	return jdbcTemplate.query(sql, listMapper, param);
//}
//
//@Override
//public int count(NoticeListSearchVO vo) {
//	if(vo.isSearch()) {//검색이라면
//		return searchCount(vo); //검색 카운트 구하는 메소드
//	}
//	else {
//		return listCount(vo);
//	}
//}
//
//@Override
//public int listCount(NoticeListSearchVO vo) {
//	String sql = "select count(*) from board";
//	return jdbcTemplate.queryForObject(sql, int.class);
//}
//
//@Override
//public int searchCount(NoticeListSearchVO vo) {
//	String sql = "select count(*) from board where instr(#1, ?) > 0";
//	sql = sql.replace("#1", vo.getType());
//	Object[] param = {vo.getKeyword()};
//	return jdbcTemplate.queryForObject(sql, int.class, param);
//}
//
}
