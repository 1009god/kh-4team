package com.kh.doran;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kh.doran.repository.BoardDao;

@SpringBootTest
public class BoardTest2 {

	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void test() {
		//번호 생성
		String sql = "select board_seq.nextval from dual";
		int boardPostNo = jdbcTemplate.queryForObject(sql, int.class);
		System.out.println("boardPostNo = " + boardPostNo);
		
		//등록
		sql = "insert into board(board_post_no, board_mem_no, "
				+ "board_title, board_content) "
				+ "values(?, ?, ?, ?)";
		Object[] param = {
				boardPostNo, 34, "테스트", "테스트"
		};
		jdbcTemplate.update(sql, param);
	}
}