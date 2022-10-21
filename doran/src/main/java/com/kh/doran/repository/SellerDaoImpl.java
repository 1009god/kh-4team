package com.kh.doran.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.doran.entity.SellerDto;

@Repository
public class SellerDaoImpl implements SellerDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insert(SellerDto sellerDto) {
	String sql="INSERT INTO SELLER(SELLER_MEM_NO,SELLER_BANK, SELLER_ACCOUNT) VALUES(?,?,?)";
	Object[] param = {sellerDto.getSellerMemNo(), sellerDto.getSellerBank(), sellerDto.getSellerAccount()};
	jdbcTemplate.update(sql, param);
	} //1 을 쓸게 아니라 멤버의 번호를 받아야함
}
