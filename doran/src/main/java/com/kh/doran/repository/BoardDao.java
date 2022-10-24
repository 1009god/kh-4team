package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.BoardDto;
import com.kh.doran.vo.BoardListSearchVO;

public interface BoardDao {
	void insert(BoardDto boardDto);
	List<BoardDto> selectList();
	List<BoardDto> selectList(BoardListSearchVO vo);
	//MemDto selectOne(int memNo);
	//	DTO selectOne(PK);
//	boolean update(DTO);
//	boolean delete(PK);
	void clear();

}
