package com.kh.doran.repository;



import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.doran.entity.SellerDto;

@Repository
public class SellerDaoImpl implements SellerDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<SellerDto> mapper = new RowMapper<SellerDto>() {

		@Override
		public SellerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			SellerDto dto = new SellerDto();
			dto.setSellerMemNo(rs.getInt("SELLER_MEM_NO"));
			dto.setSellerRegistryDate(rs.getDate("SELLER_REGISTRY_DATE"));
			dto.setSellerBank(rs.getString("SELLER_BANK"));
			dto.setSellerAccount(rs.getString("SELLER_ACCOUNT"));
			dto.setSellerCheck(rs.getString("SELLER_CHECK"));
			return dto;
		}
		
	};
	
	private ResultSetExtractor<SellerDto> extractor = new ResultSetExtractor<SellerDto>() {

		@Override
		public SellerDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				SellerDto dto = new SellerDto();
				dto.setSellerMemNo(rs.getInt("SELLER_MEM_NO"));
				dto.setSellerRegistryDate(rs.getDate("SELLER_REGISTRY_DATE"));
				dto.setSellerBank(rs.getString("SELLER_BANK"));
				dto.setSellerAccount(rs.getString("SELLER_ACCOUNT"));
				dto.setSellerCheck(rs.getString("SELLER_CHECK"));
				return dto;
			}
			else {
				return null;
				
			}
		}
		
	};
	
	@Override
	public void insert(SellerDto sellerDto) {
	String sql="INSERT INTO SELLER(SELLER_MEM_NO,SELLER_BANK, SELLER_ACCOUNT) VALUES(?,?,?)";
	Object[] param = {sellerDto.getSellerMemNo(), sellerDto.getSellerBank(), sellerDto.getSellerAccount()};
	jdbcTemplate.update(sql, param);
	} //1 을 쓸게 아니라 멤버의 번호를 받아야함

	@Override
	public SellerDto selectOne(int sellerMemNo) {
		String sql="select * from seller where seller_mem_no= ?";
		Object[] param = {sellerMemNo};
		return jdbcTemplate.query(sql, extractor, param);
	}
}
