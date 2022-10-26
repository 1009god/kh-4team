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

import com.kh.doran.entity.SellerDto;
import com.kh.doran.vo.AdminsellerDetailVO;
import com.kh.doran.vo.AdminsellerListVO;
import com.kh.doran.vo.BoardDetailVO;
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
	
	private RowMapper<AdminsellerListVO> listmapper = new RowMapper<AdminsellerListVO>() {
		@Override
		public AdminsellerListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			return AdminsellerListVO.builder()
					.memNick(rs.getString("mem_nick"))
					.sellerMemNo(rs.getInt("sellerMemNo"))
					.sellerRegistryDate(rs.getDate("seller_registry_date"))
					.sellerBank(rs.getString("seller_bank"))
					.sellerAccount(rs.getString("seller_account"))
					.sellerCheck(rs.getString("seller_check"))	
				.build();
}
};

	@Override
	public List<SellerDto> selectList() {
		String sql = "select * from seller order by seller_mem_no asc";
		return jdbcTemplate.query(sql, mapper);
	}
	
	
	private ResultSetExtractor<AdminsellerDetailVO> detailExtractor = new ResultSetExtractor<AdminsellerDetailVO>() {	
	@Override
	public AdminsellerDetailVO extractData(ResultSet rs) throws SQLException, DataAccessException {
		if(rs.next()) {
			return AdminsellerDetailVO.builder()
					.sellerMemNo(rs.getInt("seller_mem_no"))
					.memEmail(rs.getString("mem_email"))
					.memPw(rs.getString("mem_pw"))
					.memNick(rs.getString("mem_nick"))
					.memTel(rs.getString("mem_tel"))
					.memJoinDate(rs.getDate("mem_join_date"))
					.memRoute(rs.getString("mem_route"))
					.sellerRegistryDate(rs.getDate("seller_registry_date"))
					.sellerBank(rs.getString("seller_back"))
					.sellerAccount(rs.getString("seller_bank"))
					.sellerCheck(rs.getString("seller_check"))
					.build();
		}
		else {
			return null;
		}
	}
};
//	아우터 sql구문 추가 select * from mem m join seller s on mem.mem_no = seller.seller_mem_no;
	@Override
	public AdminsellerDetailVO selectOne1(int boardPostNo) {
		String sql = "";
		Object[] param = {boardPostNo};
		return jdbcTemplate.query(sql,  detailExtractor, param);
}

	@Override
	public List<AdminsellerListVO> selectList(SellerListSearchVO vo) {
		if(vo.isSearch()) {//검색이라면
			return search(vo);
		}
		else {//목록이라면
			return list(vo);
		}
	}
	
	@Override
	public List<AdminsellerListVO> search(SellerListSearchVO vo) {
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
		return jdbcTemplate.query(sql, listmapper, param);
	}
	
	@Override
	public List<AdminsellerListVO> list(SellerListSearchVO vo) {
		String sql = "select * from ( "
							+ "select rownum rn, TMP.* from ( "
								+ "select * from seller order by seller_mem_no desc "
							+ ")TMP "
						+ ") where rn between ? and ?";
		Object[] param = {vo.startRow(), vo.endRow()};
		return jdbcTemplate.query(sql, listmapper, param);
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
