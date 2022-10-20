package com.kh.doran.repository;

import com.kh.doran.entity.MemDto;

public interface MemDao {
	void insert(MemDto memDto);
	
	MemDto selectOne(String memEmail);
}
