package com.kh.doran.repository;


import java.util.List;

import com.kh.doran.entity.AddressDto;


public interface AddressDao {
	
	void insert(AddressDto addressDto); //배송지추가 메소드
	List<AddressDto> selectList(int addressMemNo); //한 명의 회원이 저장해 둔 모든 배송지를 불러내는 메소드
	int count(int addressMemNo);//한 명의 회원이 가진 배송지가 몇 개인지

}
