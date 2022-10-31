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
import com.kh.doran.vo.PjListSearchVO;

@Repository
public class DoranQDaoImpl implements DoranQDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void clear() {
		String sql = "delete doran_q";
		jdbcTemplate.update(sql);
		
	}
	
	@Override
	public int sequence() {
		String sql = "select doran_q_seq.nextval from dual";
		int doranQNo = jdbcTemplate.queryForObject(sql, int.class);
		
		return doranQNo;
	}
	
	@Override
	public void insert(DoranQDto doranQDto) {
		String sql = "insert into doran_q("
					+ "doran_q_no, "
					+ "doran_q_mem_no, "
					+ "doran_q_title , "
					+ "doran_q_content, "
					+ "doran_q_type, "
					+ "doran_q_group, "
					+ "doran_q_parent, "
					+ "doran_q_depth "
					+ ") values(?,?,?,?,?,?,?,?)";
		
		
		Object[] param= {
				doranQDto.getDoranQNo(),
				doranQDto.getDoranQMemNo(),
				doranQDto.getDoranQTitle(),
				doranQDto.getDoranQContent(),
				doranQDto.getDoranQType(),
				doranQDto.getDoranQGroup(),
				doranQDto.getDoranQParentInteger(),
				doranQDto.getDoranQDepth()
				};
		jdbcTemplate.update(sql,param);
	}
	
	@Override
	public void adminInsert(DoranQDto doranQDto) {
		String sql = "insert into doran_q("
				+ "doran_q_no, "
				+ "doran_q_mem_no, "
				+ "doran_q_title , "
				+ "doran_q_content, "
				+ "doran_q_type, "
				+ "doran_q_group, "
				+ "doran_q_parent, "
				+ "doran_q_depth "
				+ ") values(?,null,?,?,null,?,?,?)";
	
	
	Object[] param= {
			doranQDto.getDoranQNo(),
			doranQDto.getDoranQTitle(),
			doranQDto.getDoranQContent(),
			doranQDto.getDoranQGroup(),
			doranQDto.getDoranQParent(),
			doranQDto.getDoranQDepth()
			};
		jdbcTemplate.update(sql,param);
	}
	

	private RowMapper<DoranQDto> mapper = new RowMapper<DoranQDto>() {
		@Override
		public DoranQDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return DoranQDto.builder()
				.doranQNo(rs.getInt("doran_q_no"))
				.doranQMemNo(rs.getInt("doran_q_mem_no"))
				.doranQAdminNo(rs.getInt("doran_q_admin_no"))
				.doranQTitle(rs.getString("doran_q_title"))
				.doranQContent(rs.getString("doran_q_content"))
				.doranQWritetime(rs.getDate("doran_q_writetime"))
				.doranQType(rs.getString("doran_q_type"))
				.doranQProcessing(rs.getString("doran_q_processing"))
				.doranQGroup(rs.getInt("doran_q_group"))
				.doranQParent(rs.getInt("doran_q_parent"))
				.doranQDepth(rs.getInt("doran_q_depth"))
			.build();
		}
	};
	
	@Override
	public List<DoranQDto> selectList(DoranQListSearchVO vo) {
		if(vo.isSearch()) {
			return search(vo);
		}
		
		else { //목록이라면
			return list(vo);
		}
	}
	
	@Override
	public List<DoranQDto> list(DoranQListSearchVO vo) {
		String sql = "select * from ( "
					+ "    select rownum rn, TMP.* from( "
							+ "select * from doran_q "
							+ "connect by prior doran_q_no = doran_q_parent "
							+ "start with doran_q_parent is null "
							+ "order siblings by doran_q_group desc, doran_q_no asc "
						+ "					)TMP"
						+ "				) where rn between ? and ?";
		Object[] param = {vo.startRow(),vo.endRow()};
		return jdbcTemplate.query(sql, mapper, param);
	}
	
	
	// 전체 데이터 갯수
	@Override
	public int listCount(DoranQListSearchVO vo) {
		String sql = "select count(*) from doran_q ";
		return jdbcTemplate.queryForObject(sql, int.class);
	}

private ResultSetExtractor<DoranQDto> extractor = new ResultSetExtractor<DoranQDto>() {
		
		@Override
		public DoranQDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				return DoranQDto.builder()
						.doranQNo(rs.getInt("doran_q_no"))
						.doranQMemNo(rs.getInt("doran_q_mem_no"))
						.doranQAdminNo(rs.getInt("doran_q_admin_no"))
						.doranQTitle(rs.getString("doran_q_title"))
						.doranQContent(rs.getString("doran_q_content"))
						.doranQWritetime(rs.getDate("doran_q_writetime"))
						.doranQType(rs.getString("doran_q_type"))
						.doranQProcessing(rs.getString("doran_q_processing"))
						.doranQGroup(rs.getInt("doran_q_group"))
						.doranQParent(rs.getInt("doran_q_parent"))
						.doranQDepth(rs.getInt("doran_q_depth"))
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

	
	
	@Override
	public boolean delete(int doranQNo) {
		String sql = "delete doran_q where doran_q_no = ?";
		Object[] param = {doranQNo};
		return jdbcTemplate.update(sql,param) > 0;
	}

	@Override
	public List<DoranQDto> search(DoranQListSearchVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
