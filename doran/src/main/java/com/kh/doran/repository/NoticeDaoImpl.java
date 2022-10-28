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
	

	
	
	@Override
	public List<NoticeDto> selectList() {
		String sql = "select * from notice order by notice_no desc";
		return jdbcTemplate.query(sql, mapper);
	}

	@Override
	public List<NoticeDto> selectList(NoticeListSearchVO vo) {
		if(vo.isSearch()) {//검색이라면
			return search(vo);
		}
		else {//목록이라면
			return list(vo);
		}
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
		String sql = "select * from notice where notice_no = ?";
		Object[] param = {noticeNo};
		return jdbcTemplate.query(sql, extractor, param);
	}
	
	@Override
	public void insert2(NoticeDto noticeDto) {
		String sql = "insert into notice("
				+ "notice_no, notice_admin_no, notice_title, notice_content)"
				+ " values (?,?,?,?)";
		Object[] param = {
				noticeDto.getNoticeNo(), noticeDto.getNoticeAdminNo(), 
				noticeDto.getNoticeTitle(), noticeDto.getNoticeContent()
		};
		jdbcTemplate.update(sql, param);
	}
	
	@Override
	public boolean delete(int noticeNo) {
		String sql = "delete notice where notice_no = ?";
		Object[] param = {noticeNo};
		return jdbcTemplate.update(sql,param) > 0;
	}
	
	@Override
	public boolean update(NoticeDto noticeDto) {
		String sql = "update notice set notice_title=?, notice_content=? where notice_no=?";
		Object[] param = {
			noticeDto.getNoticeTitle(), noticeDto.getNoticeContent(), noticeDto.getNoticeNo()
		};
		
		return jdbcTemplate.update(sql, param) > 0;
	}
	
	@Override
	public List<NoticeDto> list(NoticeListSearchVO vo) {
		String sql = "select * from ("
				+ "select rownum rn, TMP.* from ("
				+ "select * from notice order by notice_no desc"
				+ ")TMP"
				+ ")where rn between ? and ?";
		Object[] param = {
			vo.startRow(), vo.endRow()
		};
		return jdbcTemplate.query(sql, mapper, param);
	}
	
	@Override
	public List<NoticeDto> search(NoticeListSearchVO vo) {
		String sql = "select * from ( "
							+ "select rownum rn, TMP.* from ( "
							+ "select * from notice "
							+ "where instr(#1, ?) > 0 "
							+ "order by notice_no desc"
							+ ")TMP"
							+ ") where rn between ? and ?";
		sql = sql.replace("#1", vo.getType());
		Object[] param = {vo.getKeyword(), vo.startRow(), vo.endRow()};
		return jdbcTemplate.query(sql, mapper, param);
	}

	@Override
	public int count(NoticeListSearchVO vo) {
		if(vo.isSearch()) {
			return searchCount(vo);
		}
		else {
			return listCount(vo);
		}
	}
	
	@Override
	public int listCount(NoticeListSearchVO vo) {
		String sql = "select count(*) from notice";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	@Override
	public int searchCount(NoticeListSearchVO vo) {
		String sql = "select count(*) from notice "
				+ "where instr(#1, ?) > 0";
			sql = sql.replace("#1", vo.getType());
			Object[] param = {vo.getKeyword()};
			return jdbcTemplate.queryForObject(sql, int.class, param);
	}

}
