package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.PjDto;

public interface AdminPjDao {

	List<PjDto> selectList();
	List<PjDto> selectList(String type, String keyword);
	
	PjDto selectOne(int pjNo);
	boolean delete(int pjNo);
}
