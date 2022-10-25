package com.kh.doran.repository;


import java.util.List;

import com.kh.doran.entity.AddressDto;
import com.kh.doran.entity.MemDto;


public interface AddressDao {
	
	AddressDto selectOne(int addressNo); 
	
	List<AddressDto> selectList(); // 배송지 목록 출력
	
	void insert(AddressDto addressDto); //배송지추가 메소드
	
	boolean delete(int addressNo); //배송지 삭제
	boolean update(AddressDto addressDto); //배송지 수정
	
	
	
	

}
