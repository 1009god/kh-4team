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
	@Override
	public List<PjDto> selectList() {
		String sql = "select * from pj order by pj_no asc";
		return jdbcTemplate.query(sql, mapper);
	}

	@Override
	public List<PjDto> selectList(String type, String keyword) {
		String sql = "select * from pj"
				+ "where instr("+type+", ?) > 0 "
				+ "order by "+type+" asc";
		Object[]param = {keyword};
		return jdbcTemplate .query(sql, mapper,param);
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

}


	