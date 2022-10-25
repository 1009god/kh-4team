package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.DoranQDto;

public interface DoranQDao {
	void insert(DoranQDto doranQDto);
	List<DoranQDto>selectList();
	List<DoranQDto>selectList(String type,String keyword);
//	DoranQDto selectOne(PK);
//	boolean update(DoaranQDto);
//	boolean delte(PK);
	void clear();
	
}
