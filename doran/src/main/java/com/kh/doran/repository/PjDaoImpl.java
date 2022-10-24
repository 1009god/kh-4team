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
import com.kh.doran.vo.PjListSearchVO;

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
	public List<PjDto> selectList(PjListSearchVO vo) {
		if(vo.isSearch()) {//검색
			return search(vo);
		}
		
		else if(vo.isPopular()) {//인기순
			return popular(vo);
		}
		else if(vo.isImminent()) {//마감임박순
			return imminent(vo);
		}
		else if(vo.isLast()) {//마감임박순
			return latest(vo);
		}
		else if(vo.isCategory()) {//카테고리별
			return category(vo);
		}

		else {//목록
			return list(vo);
		}
	}
	
	@Override
	public List<PjDto> search(PjListSearchVO vo) {
		String sql = "select * from ( "
				+ "select rownum rn, TMP.* from( "
					+ "select*from pj "
					+ "where instr(#1,?)>0 "
					+ "order by pj_no desc "
				+ ")TMP "
			+ ") where rn between ? and ?";
	sql=sql.replace("#1", vo.getType());
	Object[]param = {
			vo.getKeyword(), vo.startRow(),vo.endRow()
		};
	return jdbcTemplate.query(sql,mapper,param);
	}
	
	@Override
	public List<PjDto> list(PjListSearchVO vo) {
		String sql = "select * from ( "
					+ "select rownum rn, TMP.* from( "
						+ "select*from pj order by pj_no desc "
					+ ")TMP "
				+ ") where rn between ? and ?";

		Object[]param = {vo.startRow(), vo.endRow()};
		return jdbcTemplate.query(sql,mapper,param);
	}
	
	// 인기순
	@Override
	public List<PjDto> popular (PjListSearchVO vo) {
		String sql = "select * from ( "
			   + "select rownum rn, TMP.* from( " 
					+ "select*from pj order by #1 desc " 
							+ ")TMP " 
						+") where rn between ? and ?";
		sql=sql.replace("#1", vo.getSort());
		Object[] param = {vo.startRow(), vo.endRow()};
		return jdbcTemplate.query(sql,mapper,param);
	}
	
	//마감임박순
	@Override
	public List<PjDto> imminent(PjListSearchVO vo) {
		String sql = "select * from ( "
			  +  "select rownum rn, TMP.* from( "
					+ "select*from pj order by #1 asc " 
							+	")TMP " 
						+	") where rn between ? and ?";
		sql=sql.replace("#1", vo.getSort());
		Object[] param = {vo.startRow(), vo.endRow()};
		return jdbcTemplate.query(sql,mapper,param);
	}
	
	@Override
	public List<PjDto> latest(PjListSearchVO vo) {
		String sql = "select * from ( "
				  +  "select rownum rn, TMP.* from( "
						+ "select*from pj order by #1 desc " 
								+	")TMP " 
							+	") where rn between ? and ?";
			sql=sql.replace("#1", vo.getSort());
			Object[] param = {vo.startRow(), vo.endRow()};
			return jdbcTemplate.query(sql,mapper,param);
	}
	
	// 카테고리별 정렬순
	@Override
	public List<PjDto> category(PjListSearchVO vo) {
		String sql = "select * from ( " 
			    + "select rownum rn, TMP.* from( " 
					+ "select*from pj where pj_category in ? order by pj_no desc " 
								+ ")TMP " 
							+ ") where rn between ? and ?";
		Object[] param = {vo.getCategory(), vo.startRow(), vo.endRow()};
		return jdbcTemplate.query(sql,mapper,param);
	}
	
	@Override
	public int count(PjListSearchVO vo) {
		if(vo.isSearch()) {
			return searchCount(vo);
		}
		else if(vo.isImminent()) {
			return listCount(vo);
		}
		else if(vo.isCategory()) {
			return categoryCount(vo);
		}
		else {
			return listCount(vo);
		}
	}
	
	// 전체 데이터 갯수
	@Override
	public int listCount(PjListSearchVO vo) {
		String sql = "select count(*) from pj";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	// 검색 데이터 갯수
	@Override
	public int searchCount(PjListSearchVO vo) {
		String sql = "select count(*) from pj where instr(#1,?)>0";
		sql = sql.replace("#1", vo.getType());
		Object[] param = {vo.getKeyword()};
		return jdbcTemplate.queryForObject(sql, int.class,param);
	}
	
	// 카테고리별 데이터 갯수
	@Override
	public int categoryCount(PjListSearchVO vo) {
		String sql = "select count(*) from pj where pj_category in ? ";
		Object[] param = {vo.getCategory()};
		return jdbcTemplate.queryForObject(sql, int.class,param);
	}

	

	
	

}