package com.kh.doran.repository;

import java.util.List;


import com.kh.doran.entity.SellerDto;

public interface AdminSellerDao {

	List<SellerDto> selectList();
	List<SellerDto> selectList(String type, String keyword);
	
	SellerDto selectOne(int sellerNo);
	boolean delete(int sellerNo);
}
