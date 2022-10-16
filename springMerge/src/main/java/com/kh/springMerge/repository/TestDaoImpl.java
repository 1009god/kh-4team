package com.kh.springMerge.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.kh.springMerge.entity.TestDto;

public class TestDaoImpl implements TestDao {

	@Autowired
	private TestDto testDto;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<	TestDto> mapper=new RowMapper<>() {

		@Override
		public TestDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return TestDto.builder().test(rs.getInt("test1")).test2(rs.getString("test2")).build();
		}
		
	};

	@Override
	public void insert(TestDto testDto) {
		// TODO Auto-generated method stub
		
	}
}
