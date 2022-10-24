package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.BoardDto;
import com.kh.doran.vo.BoardListSearchVO;

public interface BoardDao {
	void insert(BoardDto boardDto);
	int insert2(BoardDto boardDto); //int 인 이유, 번호를 알아야 하니까 최근 게시물 올리면 디테일로 가게 해 주는
	List<BoardDto> selectList();
	List<BoardDto> selectList(BoardListSearchVO vo);
	BoardDto selectOne(int boardPostNo);
	BoardDto read(int boardPostNo); //조회수 증가까지
//	boolean update(DTO);
//	boolean delete(PK);
	
	void clear();
	boolean updateReadcount(int boardPostNo);

}
