package com.kh.doran.repository;

import com.kh.doran.entity.MemDto;

public interface MemDao {
	void insert(MemDto memDto);
	MemDto selectOne(String memEmail);
	MemDto selectOne2(int memNo);

	
	boolean profileUpdate(MemDto dto);  //구버전
	boolean editProfile(MemDto dto); //신버전
	
	//boolean accountUPdate
	


}
