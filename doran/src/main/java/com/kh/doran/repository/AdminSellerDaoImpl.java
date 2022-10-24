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
import com.kh.doran.entity.SellerDto;

@Repository
public class AdminSellerDaoImpl implements AdminSellerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<SellerDto> mapper = new RowMapper<SellerDto>() {
		@Override
		public SellerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		SellerDto dto = new SellerDto();
		dto.setSellerMemNo(rs.getInt("seller_mem_no"));
		dto.setSellerRegistryDate(rs.getDate("seller_registry_date"));
		dto.setSellerBank(rs.getString("seller_bank"));
		dto.setSellerAccount(rs.getString("seller_account"));
		dto.setSellerCheck(rs.getString("seller_check"));

		return dto;
		}
	
	};
		
	@Override
	public List<SellerDto> selectList() {
		String sql = "select * from seller order by seller_no asc";
		return jdbcTemplate.query(sql, mapper);
	}

	@Override
	public List<SellerDto> selectList(String type, String keyword) {
		String sql = "select * from seller"
					+ "where instr("+type+",?)>0"
					+ "order by "+type+" asc";
		Object[]param = {keyword};
		return jdbcTemplate.query(sql, mapper,param);
	}

	private ResultSetExtractor<SellerDto> extractor = new ResultSetExtractor<SellerDto>() {
		@Override
		public SellerDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				SellerDto dto = new SellerDto();
				dto.setSellerMemNo(rs.getInt("seller_mem_no"));
				dto.setSellerRegistryDate(rs.getDate("seller_registry_date"));
				dto.setSellerBank(rs.getString("seller_bank"));
				dto.setSellerAccount(rs.getString("seller_account"));
				dto.setSellerCheck(rs.getString("seller_check"));
				return dto;
			}
			else {
				return null;
			}
		}
	};
	
	@Override
	public SellerDto selectOne(int sellerNo) {
		String sql = "select * from seller where seller_no=?";
		Object[]param = {sellerNo};
		return jdbcTemplate.query(sql, extractor,param);
	}

	@Override
	public boolean delete(int sellerNo) {
		String sql = "delete seller where seller_no=?";
		Object[]param= {sellerNo};
		return jdbcTemplate.update(sql,param)>0;
	}

}
