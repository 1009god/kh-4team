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
	private DoranQDao dao;
	
	@BeforeEach
	
	@Test
	public void before() {
		for(int i=1;i<=10;i++) {
		dao.insert(DoranQDto.builder()
							.DoranQTitle("테스트"+i)
							.DoranQContent("테스트"+i)
							.DoranQType("취소문의")
							.DoranQProcessing("답변완료")
							.build());
		}
	}
//	@Test
//	public void test() {
//		List<DoranQDto> list = dao.selectList();
//		for(DoranQDto doranqdto:list) {
//			System.out.println(doranqdto);
//		}
//		
//		assertEquals(list.size(),10);
//	}
//	
//	@AfterEach
//	public void after() {
//		dao.clear();
//	}
}
