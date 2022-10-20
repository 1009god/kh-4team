package com.kh.doran.repository;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.doran.entity.PjDto;

@Repository
public class PjDaoImpl implements PjDao {
	
	@Autowired//주입
	private JdbcTemplate jdbcTemplate;
	
//	@Autowired
//	private PjDto pjDto; 하면...안됩니다...아예 지우면 나중에 또 까먹고 걸칠까봐 주석처리함
	
	
	private RowMapper<PjDto> mapper=new RowMapper<PjDto>(){

		@Override
		public PjDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return PjDto.builder()
					.pjNo(rs.getInt("PJ_NO"))
					.pjSellerMemNo(rs.getInt("PJ_SELLER_MEM_NO"))
					.pjCategory(rs.getString("PJ_CATEGORY"))
					.pjName(rs.getString("PJ_NAME"))
					.pjSummary(rs.getString("PJ_SUMMARY"))
					.pjTargetMoney(rs.getInt("PJ_TARGET_MONEY"))
					.pjFundingStartDate(rs.getDate("PJ_FUNDING_START_DATE"))
					.pjFundingEndDate(rs.getDate("PJ_FUNDING_END_DATE"))
					.pjEndDate(rs.getDate("PJ_END_DATE"))
					.pjLikesNumber(rs.getInt("PJ_LIKES_NUMBER"))
					.build();
		}
	
	};
	
	
	private ResultSetExtractor<PjDto> extractor=new ResultSetExtractor<PjDto>() {

		@Override
		public PjDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				return PjDto.builder()
						.pjNo(rs.getInt("PJ_NO"))
						.pjSellerMemNo(rs.getInt("PJ_SELLER_MEM_NO"))
						.pjCategory(rs.getString("PJ_CATEGORY"))
						.pjName(rs.getString("PJ_NAME"))
						.pjSummary(rs.getString("PJ_SUMMARY"))
						.pjTargetMoney(rs.getInt("PJ_TARGET_MONEY"))
						.pjFundingStartDate(rs.getDate("PJ_FUNDING_START_DATE"))
						.pjFundingEndDate(rs.getDate("PJ_FUNDING_END_DATE"))
						.pjEndDate(rs.getDate("PJ_END_DATE"))
						.pjLikesNumber(rs.getInt("PJ_LIKES_NUMBER"))
						.build();
			}
			else {
				return null;
			}
		}

	
	};


	@Override
	public PjDto selectOne(int pjNo) {
		String sql="SELECT*FROM PJ WHERE PJ_NO=?";
		Object[] param= {pjNo};
		return jdbcTemplate.query(sql,extractor,param);
	}
	
	@Override
	public List<PjDto> selectList() {
		String sql = "select*from pj order by pj_no desc";
		return jdbcTemplate.query(sql, mapper);
	}
	
	@Override
	public List<PjDto> selectList(String type, String keyword) {
		String sql = "select*from pj where instr(#1, ?) > 0 order by #1 asc";
		sql = sql.replace("#1", type);
		Object[] param = {keyword};
 		return jdbcTemplate.query(sql, mapper, param);
	}

	

}
