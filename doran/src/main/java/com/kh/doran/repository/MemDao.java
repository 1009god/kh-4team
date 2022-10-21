package com.kh.doran.repository;

import com.kh.doran.entity.MemDto;

public interface MemDao {
	void insert(MemDto memDto);
	MemDto selectOne(String memEmail);
	MemDto selectOne2(String memNo);

	
	boolean profileUpdate(MemDto dto);
	//boolean accountUPdate
	


}
