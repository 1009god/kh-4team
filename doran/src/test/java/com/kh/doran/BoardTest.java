package com.kh.doran;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.doran.entity.BoardDto;
import com.kh.doran.repository.BoardDao;

@SpringBootTest
public class BoardTest {

	@Autowired
	private BoardDao boardDao;
	
	@BeforeEach
	public void before() {
			for(int i = 1;i<=10;i++) {
				boardDao.insert(BoardDto.builder()
						.boardMemNo(35)
						.boardTitle("테스트" + i)
						.boardContent("테스트" + i)
						.build());
			}	
	}
	
	@Test
	public void test() {
		//조회
		List<BoardDto> list = boardDao.selectList();
		//검색
		//List<BoardDto> list = boardDao.selectList("board_title", "테스트");
		for(BoardDto boardDto : list) {
			System.out.println(boardDto);
		}
		
		assertEquals(list.size(), 10);
	}
	
	//@AfterEach
	public void after() {
		boardDao.clear();
	}
}