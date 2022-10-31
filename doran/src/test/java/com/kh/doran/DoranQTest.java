package com.kh.doran;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.doran.entity.DoranQDto;
import com.kh.doran.repository.DoranQDao;

@SpringBootTest
public class DoranQTest {

	@Autowired
	private DoranQDao doranQDao;
	
	@Test
	public void before() {
		for(int i=1;i<=10;i++) {
		doranQDao.insert(DoranQDto.builder()
							.doranQMemNo(36)
							.doranQTitle("테스트"+i)
							.doranQContent("테스트"+i)
							.doranQType("기타문의")
							.build());
		}
	}

//	@Test
//	public void test() {
//		//조회
//		List<DoranQDto> list = doranQDao.selectList(null);
//		for(DoranQDto doranQDto : list) {
//			System.out.println(doranQDto);
//		}
//		
//		assertEquals(list.size(), 20);
//	}
	
	
//	@AfterEach
//	public void after() {
//		doranQDao.clear();
//	}
	
}
