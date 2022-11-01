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

import com.kh.doran.entity.ReplyDto;
import com.kh.doran.vo.ReplyListVO;

@Repository
public class ReplyDaoImpl implements ReplyDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<ReplyDto> mapper = new RowMapper<ReplyDto>() {
		@Override
		public ReplyDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return ReplyDto.builder()
										.replyNo(rs.getInt("reply_no"))
										.replyBoardPostNo(rs.getInt("reply_board_post_no"))
										.replyMemNo(rs.getInt("reply_mem_no"))
										.replyContent(rs.getString("reply_content"))
										.replyWriteTime(rs.getDate("reply_writetime"))
									.build();
		}
	};
	
	private RowMapper<ReplyListVO> listMapper = new RowMapper<ReplyListVO>() {
		@Override
		public ReplyListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			return ReplyListVO.builder()
										.replyNo(rs.getInt("reply_no"))
										.replyBoardPostNo(rs.getInt("reply_board_post_no"))
										.replyMemNo(rs.getInt("reply_mem_no"))
										.replyContent(rs.getString("reply_content"))
										.replyWriteTime(rs.getDate("reply_writetime"))
										.memNick(rs.getString("mem_nick"))			
									.build();
		}
	};
	
	@Override
	public void insert(ReplyDto replyDto) {
		String sql = "insert into reply("
							+ "reply_no, reply_board_post_no, reply_mem_no, reply_content)"
							+ "values(reply_seq.nextval, ?, ?, ?)";
		Object[] param = {
				replyDto.getReplyBoardPostNo(), replyDto.getReplyMemNo(), 
				replyDto.getReplyContent()
		};
		jdbcTemplate.update(sql, param);
	}
	
	@Override
	public List<ReplyListVO> selectList(int replyBoardPostNo) {
		String sql = "select R.*, M.mem_nick "
				+ "from reply R left outer join "
				+ "mem M on R.reply_mem_no = M.mem_no "
						+ "where reply_board_post_no = ? "
						+ "order by reply_writetime";
		Object[] param = {replyBoardPostNo};
		return jdbcTemplate.query(sql, listMapper, param);
	}
	
	@Override
	public boolean update(ReplyDto replyDto) {
		String sql = "update reply "
							+ "set reply_content=? "
							+ "where reply_no=?";
		Object[] param = {
			replyDto.getReplyContent(), replyDto.getReplyNo()	
		};
		return jdbcTemplate.update(sql, param) > 0;
	}
	
	@Override
	public boolean delete(int replyNo) {
		String sql = "delete reply where reply_no = ?";
		Object[] param = {replyNo};
		return jdbcTemplate.update(sql, param) > 0;
	}
	
	private ResultSetExtractor<ReplyDto> extractor = new ResultSetExtractor<ReplyDto>() {
		
		@Override
		public ReplyDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				return ReplyDto.builder()
						.replyNo(rs.getInt("reply_no"))
						.replyBoardPostNo(rs.getInt("reply_board_post_no"))
						.replyMemNo(rs.getInt("reply_mem_no"))
						.replyContent(rs.getString("reply_content"))
						.replyWriteTime(rs.getDate("reply_writetime"))
					.build();
			}
			else {
				return null;
			}
		}
	};
	
	@Override
	public ReplyDto selectOne(int replyNo) {
		String sql = "select * from reply where reply_no=?";
		Object[] param = {replyNo};
		return jdbcTemplate.query(sql, extractor, param);
	}
}
