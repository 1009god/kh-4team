package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.BoardDto;
import com.kh.doran.entity.MemDto;

public interface BoardDao {
	void insert(BoardDto boardDto);
	List<BoardDto> selectList();
	List<BoardDto> selectList(String type, String keyword);
	//MemDto selectOne(int memNo);
	//	DTO selectOne(PK);
//	boolean update(DTO);
//	boolean delete(PK);
	void clear();

}
