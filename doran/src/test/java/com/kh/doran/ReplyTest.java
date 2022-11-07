package com.kh.doran;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.doran.entity.ReplyDto;
import com.kh.doran.repository.ReplyDao;
import com.kh.doran.vo.ReplyListVO;

@SpringBootTest
public class ReplyTest {
	
	@Autowired
	private ReplyDao replyDao;
	
	@BeforeEach
	public void before() {
		//insert
		for(int i=1; i <= 100; i++) {
			replyDao.insert(ReplyDto.builder()
										.replyBoardPostNo(8026)
										.replyMemNo(35)
										.replyContent("댓글테스트" + i)
									.build());
		}
	}
	
	@Test
	public void test() {
		List<ReplyListVO> list = replyDao.selectList(8026);
	}

}
