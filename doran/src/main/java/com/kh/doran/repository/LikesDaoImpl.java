package com.kh.doran.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kh.doran.entity.LikesDto;

public class LikesDaoImpl implements LikesDao {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insert(LikesDto dto) {
		String sql="insert into likes(likes_pj_no, likes_mem_no) values(?, ?)";
		Object[] param= {dto.getLikesPjNo(), dto.getLikesMemNo()};
		jdbcTemplate.update(sql,param);
		
	}

	@Override
	public void delete(LikesDto dto) {
		String sql="delete likes where likes_pj_no=? and likes_mem_no=?";
		Object[] param= {dto.getLikesPjNo(),dto.getLikesMemNo()};
		jdbcTemplate.update(sql,param);
	}

	
	@Override
	public boolean check(LikesDto dto) {
		String sql="select count(*) from likes where likes_pj_no=? and likes_mem_no=?";
		Object[] param= {dto.getLikesPjNo(),dto.getLikesMemNo()};
		int count=jdbcTemplate.queryForObject(sql, int.class, param);//좋아요 상태면 1. 아닌 상태면 0
		return count==1;//좋아요 상태면 1. 아닌 상태면 0
	}
	
	@Override
	public int count(int pjNo) {
		String sql="select count(*) from likes where likes_pj_no=?";
		Object[] param= {pjNo};
		return jdbcTemplate.queryForObject(sql, int.class,param);
	}

}
