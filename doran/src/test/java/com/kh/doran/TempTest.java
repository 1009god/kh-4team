package com.kh.doran;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.doran.repository.PjDao;
import com.kh.doran.vo.PjListSearchVO;

@SpringBootTest
public class TempTest {
	
	@Autowired
	private PjDao pjDao;
	
	@Test
	public void test() {
		for(PjListSearchVO vo : pjDao.popular(PjListSearchVO.builder().build())){
			System.out.println(vo.getPjNo() + " / " + vo.getPjLikesNumber());
		}
	}
}
