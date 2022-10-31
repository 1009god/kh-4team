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

import com.kh.doran.entity.PjDto;
import com.kh.doran.vo.AdminpjListVO;
import com.kh.doran.vo.BoardListSearchVO;
import com.kh.doran.vo.PjListSearchVO;

@Repository
public class AdminPjDaoImpl implements AdminPjDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<PjDto> mapper = new RowMapper<PjDto>() {
		@Override
		public PjDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		PjDto dto = new PjDto();
		dto.setPjNo(rs.getInt("pj_no"));
		dto.setPjSellerMemNo(rs.getInt("pj_seller_mem_no"));
		dto.setPjCategory(rs.getString("pj_category"));
		dto.setPjName(rs.getString("pj_name"));
		dto.setPjSummary(rs.getString("pj_summary"));
		dto.setPjTargetMoney(rs.getInt("pj_target_money"));
		dto.setPjFundingStartDate(rs.getDate("pj_funding_start_date"));
		dto.setPjFundingEndDate(rs.getDate("pj_funding_end_date"));
		dto.setPjEndDate(rs.getDate("pj_end_date"));
		
		return dto;
		}
	
	};
	
	private RowMapper<AdminpjListVO> listmapper = new RowMapper<AdminpjListVO>() {
		@Override
		public AdminpjListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			return AdminpjListVO.builder()
					.pjNo(rs.getInt("pj_no"))
					.pjSellerMemNo(rs.getInt("pj_seller_mem_no"))
					.pjCategory(rs.getString("pj_category"))
					.pjName(rs.getString("pj_name"))
					.pjTargetMoney(rs.getInt("pj_target_money"))
					.memNick(rs.getString("mem_nick"))
					.pjFundingStartDate(rs.getDate("pj_funding_start_date"))
				.build();
}
};
	@Override
	public List<PjDto> selectList() {
		String sql = "select * from pj order by pj_no asc";
		return jdbcTemplate.query(sql, mapper);
	}

	@Override
	public List<AdminpjListVO> selectList(PjListSearchVO vo) {
		if(vo.isSearch()) {//검색이라면
			return search(vo);
		}
		else {//목록이라면
			return list(vo);
		}
	}

	private ResultSetExtractor<PjDto> extractor = new ResultSetExtractor<PjDto>() {
		@Override
		public PjDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				PjDto dto = new PjDto();
				dto.setPjNo(rs.getInt("pj_no"));
				dto.setPjSellerMemNo(rs.getInt("pj_seller_mem_no"));
				dto.setPjCategory(rs.getString("pj_category"));
				dto.setPjName(rs.getString("pj_name"));
				dto.setPjSummary(rs.getString("pj_summary"));
				dto.setPjTargetMoney(rs.getInt("pj_target_money"));
				dto.setPjFundingStartDate(rs.getDate("pj_funding_start_date"));
				dto.setPjFundingEndDate(rs.getDate("pj_funding_end_date"));
				dto.setPjEndDate(rs.getDate("pj_end_date"));
				return dto;
			}
			else {
				return null;
			}
		}
	};
	
	@Override
	public PjDto selectOne(int pjNo) {
		String sql = "select *from pj where pj_no=?";
		Object[]param = {pjNo};
		return jdbcTemplate.query(sql, extractor,param);
	}

	@Override
	public boolean delete(int pjNo) {
		String sql = "delete pj where pj_no=?";
		Object[]param= {pjNo};
		return jdbcTemplate.update(sql,param)>0;
	}
	
	@Override
	public List<AdminpjListVO> search(PjListSearchVO vo) {
		String sql = "";
		sql = sql.replace("#1", vo.getType());
		Object[] param = {
			vo.getKeyword(), vo.startRow(), vo.endRow()
		};
		return jdbcTemplate.query(sql, listmapper, param);
	}
	
	@Override
	public List<AdminpjListVO> list(PjListSearchVO vo) {
		String sql = "select rownum , p.pj_no,p.pj_seller_mem_no,p.pj_category,p.pj_name,p.pj_target_money,p.pj_funding_start_date,"
				+ "(select m.mem_nick from mem m where m.mem_no=p.pj_seller_mem_no) as mem_nick from pj p "
				+ " where rownum between ? and ? order by pj_no desc";
		Object[] param = {vo.startRow(), vo.endRow()};
		return jdbcTemplate.query(sql, listmapper, param);
	}

	@Override
	public int count(PjListSearchVO vo) {
		if(vo.isSearch()) {//검색이라면
			return searchCount(vo); //검색 카운트 구하는 메소드
		}
		else {
			return listCount(vo);
		}
	}
	
	@Override
	public int listCount(PjListSearchVO vo) {
		String sql = "select count(*) from pj";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	@Override
	public int searchCount(PjListSearchVO vo) {
		String sql = "select count(*) from pj where instr(#1, ?) > 0";
		sql = sql.replace("#1", vo.getType());
		Object[] param = {vo.getKeyword()};
		return jdbcTemplate.queryForObject(sql, int.class, param);
	}
	

}


	