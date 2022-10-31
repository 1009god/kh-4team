package com.kh.doran.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.doran.entity.LikesDto;
import com.kh.doran.vo.likeVO;

@Repository
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

	@Override
	public void refresh(int pjNo) {
		String sql="UPDATE PJ SET PJ_LIKES_NUMBER=(SELECT COUNT(*) FROM LIKES "
				+ "WHERE LIKES_PJ_NO=?) WHERE PJ_NO=?";
		Object[] param= {pjNo, pjNo};
		jdbcTemplate.update(sql,param);
	}
	
	
	//좋아요 mapper
	private RowMapper<likeVO> likeMapper = new RowMapper<likeVO>() {
		
		@Override
		public likeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return likeVO.builder()
					.pjNo(rs.getInt("PJ_NO"))
					.pjName(rs.getString("PJ_NAME"))
					.pjCategory(rs.getString("PJ_CATEGORY"))
					.sellerMemNo(rs.getInt("SELLER_MEM_NO"))
					.build();
		}
	};
	
	
	//좋아요 list
	@Override
	public List<likeVO> likeList(int sellerMemNo) {
		String sql = "select * from like_view where LIKES_MEM_NO = ?";
		Object[] param = {sellerMemNo};
		return jdbcTemplate.query(sql, likeMapper, param);
	}
	
}
