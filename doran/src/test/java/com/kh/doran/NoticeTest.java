package com.kh.doran;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.doran.entity.NoticeDto;
import com.kh.doran.repository.NoticeDao;

@SpringBootTest
public class NoticeTest {
	
	@Autowired
	private NoticeDao noticeDao;
	
	@BeforeEach
	public void beforeEach() {
		for(int i = 1;i<=50;i++) {
			noticeDao.insert(NoticeDto.builder()
					.noticeAdminNo(2)
					.noticeTitle("테스트" + i)
					.noticeContent("테스트" + i)
					.build());
		}	
}
	
	@Test
	public void test() {
		//조회
		List<NoticeDto> list = noticeDao.selectList();
		//List<NoticeDto> list = noticeDao.selectList("notice_title", "테스트");
		for(NoticeDto noticeDto : list) {
			System.out.println(noticeDto);
		}
		
		assertEquals(list.size(), 10);
	}
	
	
	//@AfterEach
	public void after() {
		noticeDao.clear();
	}

}
