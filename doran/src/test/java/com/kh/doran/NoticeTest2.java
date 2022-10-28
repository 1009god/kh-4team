package com.kh.doran;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kh.doran.entity.NoticeDto;
import com.kh.doran.repository.NoticeDao;

@SpringBootTest
public class NoticeTest2 {
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void test() {
		//번호 생성
		String sql = "select notice_seq.nextval from dual";
		int noticeNo = jdbcTemplate.queryForObject(sql, int.class);
		System.out.println("noticeNo = " + noticeNo);
		
		//등록
		sql = "insert into notice("
				+ "notice_no, notice_admin_no, notice_title, notice_content)"
				+ " values (?,?,?,?)";
		Object[] param = {
				noticeNo, 1, "공지", "내용"
		};
		jdbcTemplate.update(sql, param);
	}


}
