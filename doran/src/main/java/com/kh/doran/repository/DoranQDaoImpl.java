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

import com.kh.doran.entity.DoranQDto;
import com.kh.doran.vo.DoranQListSearchVO;

@Repository
public class DoranQDaoImpl implements DoranQDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insert(DoranQDto doranQDto) {
		String sql = "insert into doran_q("
					+ "doran_q_no,doran_q_mem_no,doran_q_admin_no, doran_q_title, doran_q_content,"
					+ "doran_q_type, doran_q_processing"
					+ ") values(doran_q_seq.nextval,?,?,?,?,?,?)";
		Object[] param= {doranQDto.getDoranQTitle(),doranQDto.getDoranQmemNo(),doranQDto.getDoranQadminNo(),
				doranQDto.getDoranQContent(),doranQDto.getDoranQType(),
				doranQDto.getDoranQProcessing(),doranQDto.getDoranQNo()
				};
		jdbcTemplate.update(sql,param);
	}

	private RowMapper<DoranQDto> mapper = new RowMapper<DoranQDto>() {
		@Override
		public DoranQDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return DoranQDto.builder()
			.doranQNo(rs.getInt("doran_q_no"))
			.doranQmemNo(rs.getInt("doran_q_mem_no"))
			.doranQadminNo(rs.getInt("doran_q_admin_no"))
			.doranQTitle(rs.getString("doran_q_title"))
			.doranQContent(rs.getString("doran_q_content"))
			.doranQWritetime(rs.getDate("doran_q_write_time"))
			.doranQType(rs.getString("doran_q_type"))
			.doranQProcessing(rs.getString("doran_q_processing"))
			.build();
		}
	};
	
	@Override
	public List<DoranQDto> selectList() {
		String sql = "select * from doran_q order by doran_q_no desc";
		return jdbcTemplate.query(sql, mapper);
	}

//	@Override
//	public List<DoranQDto> selectList(DoranQListSearchVO vo) {
//		if(vo.isSearch()) {
//			return search(vo);
//		}
//		
//		else { //목록이라면
//			return list(vo);
//		}
//	}

private ResultSetExtractor<DoranQDto> extractor = new ResultSetExtractor<DoranQDto>() {
		
		@Override
		public DoranQDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				return DoranQDto.builder()
						.doranQNo(rs.getInt("doran_q_no"))
						.doranQmemNo(rs.getInt("doran_q_mem_no"))
						.doranQadminNo(rs.getInt("doran_q_admin_no"))
						.doranQTitle(rs.getString("doran_q_title"))
						.doranQContent(rs.getString("doran_q_content"))
						.doranQWritetime(rs.getDate("doran_q_write_time"))
						.doranQType(rs.getString("doran_q_type"))
						.doranQProcessing(rs.getString("doran_q_processing"))
						.build();
			}
			else {
				return null;
			}
		}
	};

	@Override
	public DoranQDto selectOne(int doranQNo) {
		String sql = "select * from doran_q where doran_q_no = ?";
		Object[] param = {doranQNo};
		return jdbcTemplate.query(sql,  extractor, param);
	}
//	@Override
//	public int insert2(DoranQDto doranQDto) {
//		//번호를 미리 생성한 뒤 등록하는 기능
//				String sql = "select doran_q_seq.nextval from dual";
//				int doranQNo = jdbcTemplate.queryForObject(sql, int.class);
//				
//				//등록 시퀀스 생성 xx
//				sql = "insert into doran_q("
//						+ "doran_q_no, doran_q_title, doran_q_content,"
//						+ "doran_q_type, doran_q_processing"
//						+ ") values(?, ?, ?, ?, ?)";
//			Object[] param= {doranQDto.getDoranQTitle(),
//					doranQDto.getDoranQContent(),doranQDto.getDoranQType(),
//					doranQDto.getDoranQProcessing()
//					};
//				jdbcTemplate.update(sql, param);
//				
//				return doranQNo;
//			}
	
	@Override
	public boolean update(DoranQDto doranQDto) {
		String sql = "update doran_q set doran_q_title=?, doran_q_content=?,doran_q_type where doran_q_no=?";
		Object[] param = {
				doranQDto.getDoranQTitle(), doranQDto.getDoranQContent(), doranQDto.getDoranQType(),doranQDto.getDoranQNo()
		};
		
		return jdbcTemplate.update(sql, param) > 0;
	}
	
	@Override
	public boolean delete(int doranQNo) {
		String sql = "delete doran_q where doran_q_no = ?";
		Object[] param = {doranQNo};
		return jdbcTemplate.update(sql,param) > 0;
	}
	

	
//	@Override
//	public List<DoranQDto> search(DoranQListSearchVO vo) {
//		String sql = "select * from ( "
//				+ "select rownum rn, TMP.* from ( "
//					+ "select * from doran_q "
//					+ "where instr(#1, ?) > 0 "
//					+ "order by doran_q_no desc "
//					+ ")TMP "
//					+ ") where rn between ? and ? ";
//			sql = sql.replace("#1", vo.getType());
//			Object[] param = {
//				vo.getKeyword(), vo.startRow(), vo.endRow()
//				};
//		return jdbcTemplate.query(sql, mapper, param);
//	}
//	@Override
//	public List<DoranQDto> list(DoranQListSearchVO vo) {
//		String sql = "select * from ( "
//				+ "select rownum rn, TMP.* from ( "
//					+ "select * from doran_q order by doran_q_no desc "
//				+ ")TMP "
//			+ ") where rn between ? and ?";
//			Object[] param = {vo.startRow(), vo.endRow()};
//			return jdbcTemplate.query(sql, mapper, param);
//}
//
//	@Override
//	public int count(DoranQListSearchVO vo) {
//		if(vo.isSearch()) {//검색이라면
//			return searchCount(vo); //검색 카운트 구하는 메소드
//		}
//		else {
//			return listCount(vo);
//		}
//	}
//
//	@Override
//	public int listCount(DoranQListSearchVO vo) {
//		String sql = "select count(*) from doran_q";
//		return jdbcTemplate.queryForObject(sql, int.class);
//	}
//	
//	@Override
//	public int searchCount(DoranQListSearchVO vo) {
//		String sql = "select count(*) from doran_q where instr(#1, ?) > 0";
//		sql = sql.replace("#1", vo.getType());
//		Object[] param = {vo.getKeyword()};
//		return jdbcTemplate.queryForObject(sql, int.class, param);
//	}


}
