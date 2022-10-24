package com.kh.doran.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.doran.entity.AddressDto;


@Repository
public class AddressDaoImpl implements AddressDao {


//	@Override
//	public void insert(MemDto memDto) {
//		// TODO Auto-generated method stub
//		
//	}

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<AddressDto> mapper=new RowMapper<>() {

		@Override
		public AddressDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return AddressDto.builder()
										.addressNo(rs.getInt("ADDRESS_NO"))
										.addressMemNo(rs.getInt("ADDRESS_MEM_NO"))
										.addressName(rs.getString("ADDRESS_NAME"))
										.addressTel(rs.getString("ADDRESS_TEL"))
										.addressPost(rs.getString("ADDRESS_POST"))
										.addressBasic(rs.getString("ADDRESS_BASIC"))
										.addressDetail(rs.getString("ADDRESS_DETAIL"))
										.build();
		}	
	};
	
	private ResultSetExtractor<AddressDto> extractor=new ResultSetExtractor<>() {

		@Override
		public AddressDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				return AddressDto.builder()
						.addressNo(rs.getInt("ADDRESS_NO"))
						.addressMemNo(rs.getInt("ADDRESS_MEM_NO"))
						.addressName(rs.getString("ADDRESS_NAME"))
						.addressTel(rs.getString("ADDRESS_TEL"))
						.addressPost(rs.getString("ADDRESS_POST"))
						.addressBasic(rs.getString("ADDRESS_BASIC"))
						.addressDetail(rs.getString("ADDRESS_DETAIL"))
						.build();
			}
			else {
				return null;
			}
		}	
	};

}

