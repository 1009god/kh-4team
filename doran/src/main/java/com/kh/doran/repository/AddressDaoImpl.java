package com.kh.doran.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Repository;

import com.kh.doran.entity.AddressDto;
import com.kh.doran.entity.MemDto;


@Repository
public class AddressDaoImpl implements AddressDao {


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

	//배송지 등록
	@Override
	public void insert(AddressDto addressDto) {
		String sql = "insert into address values (address_seq.nextval, ?, ?, ?, ?, ?, ?)";
		Object[] param = {
				addressDto.getAddressMemNo(), 
				addressDto.getAddressName(),
				addressDto.getAddressTel(),
				addressDto.getAddressPost(),
				addressDto.getAddressBasic(),
				addressDto.getAddressDetail()				
		};		
		jdbcTemplate.update(sql, param);
	}
	
	//배송지 목록
	@Override
	public List<AddressDto> selectList() {
		String sql = "select * from ("
				+ "select rownum rn, TMP.* from ("
					+ "select * from address order by address_no desc"
				+ ")TMP"
			+ ") where rn between 1 and 5";
		return jdbcTemplate.query(sql, mapper);
	}
	
	//배송지 삭제
	@Override
	public boolean delete(int addressNo) {
		String sql = "delete address where address_no = ?";
		Object[] param = {addressNo};
		return jdbcTemplate.update(sql, param) > 0;
	}

	
	//배송지 selectOne
	@Override
	public AddressDto selectOne(int addressNo) {
		String sql = "select * from address where address_no = ?";
		Object[] param = {addressNo};
		return jdbcTemplate.query(sql, extractor, param);
	}

	
	//배송지 수정
	@Override
	public boolean update(AddressDto addressDto) {
		String sql = "update address set "
					+ "address_name = ?, "
					+ "address_post = ?, "
					+ "address_basic = ?, "
					+ "address_detail = ?, "
					+ "address_tel = ? "
				+ "where "
					+ "address_no = ?";
		Object[] param = {			
				addressDto.getAddressName(),
				addressDto.getAddressPost(),
				addressDto.getAddressBasic(),
				addressDto.getAddressDetail(),				
				addressDto.getAddressTel(),
				addressDto.getAddressNo()
			};		
		return jdbcTemplate.update(sql, param) > 0 ;
	}
	



}

