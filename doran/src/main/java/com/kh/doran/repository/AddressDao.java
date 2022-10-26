package com.kh.doran.repository;


import java.util.List;

import com.kh.doran.entity.AddressDto;
import com.kh.doran.entity.MemDto;


public interface AddressDao {
	
	AddressDto selectOne(int addressNo); 
	
	List<AddressDto> selectList(); // 배송지 목록 출력
	
	void insert(AddressDto addressDto); //배송지추가 메소드

	List<AddressDto> selectList(int addressMemNo); //한 명의 회원이 저장해 둔 모든 배송지를 불러내는 메소드
	int count(int addressMemNo);//한 명의 회원이 가진 배송지가 몇 개인지

	
	boolean delete(int addressNo); //배송지 삭제
	boolean update(AddressDto addressDto); //배송지 수정
	
	
	
	


}
