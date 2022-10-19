package com.kh.doran.repository;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.kh.doran.entity.PjDto;

public class PjDaoImpl implements PjDao {
	
	@Autowired//주입
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PjDto pjDto;

	
	private RowMapper<PjDto> mapper=new RowMapper<>
}
