package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.BoardDto;
import com.kh.doran.vo.BoardListSearchVO;

public interface BoardDao {
	void insert(BoardDto boardDto);
	List<BoardDto> selectList();
	List<BoardDto> selectList(BoardListSearchVO vo);
	BoardDto selectOne(int boardPostNo);
	BoardDto read(int boardPostNo); //조회수 증가까지

//	boolean update(DTO);
//	boolean delete(PK);
	
	void clear();
	boolean updateReadcount(int boardPostNo);

}
