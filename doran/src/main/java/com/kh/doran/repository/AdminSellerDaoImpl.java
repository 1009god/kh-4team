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

import com.kh.doran.entity.MemDto;
import com.kh.doran.entity.SellerDto;
import com.kh.doran.vo.MemListSearchVO;
import com.kh.doran.vo.SellerListSearchVO;

@Repository
public class AdminSellerDaoImpl implements AdminSellerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<SellerDto> mapper = new RowMapper<SellerDto>() {
		@Override
		public SellerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		SellerDto dto = new SellerDto();
		dto.setSellerMemNo(rs.getInt("seller_mem_no"));
		dto.setSellerRegistryDate(rs.getDate("seller_registry_date"));
		dto.setSellerBank(rs.getString("seller_bank"));
		dto.setSellerAccount(rs.getString("seller_account"));
		dto.setSellerCheck(rs.getString("seller_check"));

		return dto;
		}
	
	};
		
	@Override
	public List<SellerDto> selectList() {
		String sql = "select * from seller order by seller_mem_no asc";
		return jdbcTemplate.query(sql, mapper);
	}

	@Override
	public List<SellerDto> selectList(SellerListSearchVO vo) {
		if(vo.isSearch()) {//검색이라면
			return search(vo);
		}
		else {//목록이라면
			return list(vo);
		}
	}
	
	@Override
	public List<SellerDto> search(SellerListSearchVO vo) {
		String sql = "select * from ( "
							+ "select rownum rn, TMP.* from ( "
								+ "select * from seller "
								+ "where instr(#1, ?) > 0 "
								+ "order by seller_mem_no desc "
								+ ")TMP "
								+ ") where rn between ? and ? ";
		sql = sql.replace("#1", vo.getType());
		Object[] param = {
			vo.getKeyword(), vo.startRow(), vo.endRow()
		};
		return jdbcTemplate.query(sql, mapper, param);
	}
	
	@Override
	public List<SellerDto> list(SellerListSearchVO vo) {
		String sql = "select * from ( "
							+ "select rownum rn, TMP.* from ( "
								+ "select * from seller order by seller_mem_no desc "
							+ ")TMP "
						+ ") where rn between ? and ?";
		Object[] param = {vo.startRow(), vo.endRow()};
		return jdbcTemplate.query(sql, mapper, param);
	}

	private ResultSetExtractor<SellerDto> extractor = new ResultSetExtractor<SellerDto>() {
		@Override
		public SellerDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				SellerDto dto = new SellerDto();
				dto.setSellerMemNo(rs.getInt("seller_mem_no"));
				dto.setSellerRegistryDate(rs.getDate("seller_registry_date"));
				dto.setSellerBank(rs.getString("seller_bank"));
				dto.setSellerAccount(rs.getString("seller_account"));
				dto.setSellerCheck(rs.getString("seller_check"));
				return dto;
			}
			else {
				return null;
			}
		}
	};
	
	@Override
	public SellerDto selectOne(int sellerNo) {
		String sql = "select * from seller where seller_mem_no=?";
		Object[]param = {sellerNo};
		return jdbcTemplate.query(sql, extractor,param);
	}

	@Override
	public boolean delete(int sellerNo) {
		String sql = "delete seller where seller_mem_no=?";
		Object[]param= {sellerNo};
		return jdbcTemplate.update(sql,param)>0;
	}
	
	@Override
	public int count(SellerListSearchVO vo) {
		if(vo.isSearch()) {//검색이라면
			return searchCount(vo); //검색 카운트 구하는 메소드
		}
		else {
			return listCount(vo);
		}
	}
	
	@Override
	public int listCount(SellerListSearchVO vo) {
		String sql = "select count(*) from seller";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	@Override
	public int searchCount(SellerListSearchVO vo) {
		String sql = "select count(*) from seller where instr(#1, ?) > 0";
		sql = sql.replace("#1", vo.getType());
		Object[] param = {vo.getKeyword()};
		return jdbcTemplate.queryForObject(sql, int.class, param);
	}

}
