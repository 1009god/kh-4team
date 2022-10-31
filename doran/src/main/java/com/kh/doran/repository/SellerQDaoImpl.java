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
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.doran.entity.SellerQDto;
import com.kh.doran.vo.BoardListSearchVO;
import com.kh.doran.vo.SellerQListSearchVO;

@Repository
public class SellerQDaoImpl implements SellerQDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	// Mapper
	private RowMapper<SellerQDto> mapper = new RowMapper<SellerQDto>() {		
		@Override
		public SellerQDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return SellerQDto.builder()
					.sellerQNo(rs.getInt("SELLER_Q_NO"))
					.sellerQMemNo(rs.getInt("SELLER_Q_MEM_NO"))
					.sellerQPjNo(rs.getInt("SELLER_Q_PJ_NO"))
					.sellerQTitle(rs.getString("SELLER_Q_TITLE"))
					.sellerQContent(rs.getString("SELLER_Q_CONTENT"))
					.sellerQWritetime(rs.getDate("SELLER_Q_WRITETIME"))
					.build();
		}
	};
	
	//extractor 
	private ResultSetExtractor<SellerQDto> extractor = new ResultSetExtractor<SellerQDto>() {
		
		@Override
		public SellerQDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				return SellerQDto.builder()
						.sellerQNo(rs.getInt("SELLER_Q_NO"))
						.sellerQMemNo(rs.getInt("SELLER_Q_MEM_NO"))
						.sellerQPjNo(rs.getInt("SELLER_Q_PJ_NO"))
						.sellerQTitle(rs.getString("SELLER_Q_TITLE"))
						.sellerQContent(rs.getString("SELLER_Q_CONTENT"))
						.sellerQWritetime(rs.getDate("SELLER_Q_WRITETIME"))
						.build();				
			}
			else {
				return null;
			}
		}
	};

	//insert 판매자에게 문의 작성
	@Override
	public void insert(SellerQDto sellerQDto) {
		String sql = "insert into seller_q values(seller_q_seq.nextval,?, ? , ? , ? , sysdate)";
		Object[] param = {sellerQDto.getSellerQMemNo(),
									sellerQDto.getSellerQPjNo(),
									sellerQDto.getSellerQTitle(), 
									sellerQDto.getSellerQContent()};
		jdbcTemplate.update(sql, param);	
	}

	@Override
	public List<SellerQDto> selectList(@RequestParam int sellerQPjNo) {
		String sql = "select * from seller_q where SELLER_Q_PJ_NO = ? ORDER BY SELLER_Q_NO DESC";	
		Object[] param = {sellerQPjNo};
		return jdbcTemplate.query(sql, mapper, param);
	}
	
	
	
	
	
	
//	@Override
//	public int searchCount(SellerQListSearchVO vo) {
//		String sql = "select count(*) from board where SELLER_Q_PJ_NO = ? ORDER BY SELLER_Q_NO DESC";
//		sql = sql.replace("#1", vo.getType());
//		Object[] param = {vo.get};
//		return jdbcTemplate.queryForObject(sql, int.class, param);
//	}
	
	
	
	
	
	

	

}
