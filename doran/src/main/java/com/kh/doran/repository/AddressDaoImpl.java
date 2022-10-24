package com.kh.doran.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.doran.entity.AddressDto;


@Repository
public class AddressDaoImpl implements AddressDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
				//sql 회원번호 부러와서 입력 어케??
		};
		jdbcTemplate.update(sql, param);
	}
	
	
	

}
