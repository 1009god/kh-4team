package com.kh.doran.repository;


import java.util.List;

import com.kh.doran.entity.AddressDto;


public interface AddressDao {
	
	void insert(AddressDto addressDto); //배송지추가 메소드
	
	List<AddressDto> selectList(); // 배송지 목록 출력

}
