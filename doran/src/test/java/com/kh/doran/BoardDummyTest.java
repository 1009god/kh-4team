package com.kh.doran;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.doran.entity.BoardDto;
import com.kh.doran.repository.BoardDao;

@SpringBootTest
public class BoardDummyTest {

	@Autowired
	private BoardDao boardDao;
	
	@Test
	public void before() {
			for(int i = 1;i<=7905;i++) {
				boardDao.insert(BoardDto.builder()
						.boardMemNo(35)
						.boardTitle("테스트" + i)
						.boardContent("테스트" + i)
						.build());
			}	
	}
	
	@BeforeEach
	public void after() {
		boardDao.clear();
	}
}