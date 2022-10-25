package com.kh.doran.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.doran.entity.DoranQDto;

@Repository
public class DoranQDaoImpl implements DoranQDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insert(DoranQDto doranQDto) {
		String sql = "insert into doran_q("
					+ "doran_q_no, doran_q_title, doran_q_content,"
					+ "doran_q_type, doran_q_processing"
					+ ") values(doran_q_seq.nextval, ?, ?, ?, ?)";
		Object[] param= {doranQDto.getDoranQTitle(),
				doranQDto.getDoranQContent(),doranQDto.getDoranQType(),
				doranQDto.getDoranQProcessing()
				};
		jdbcTemplate.update(sql,param);
	}

	private RowMapper<DoranQDto> mapper = new RowMapper<DoranQDto>() {
		@Override
		public DoranQDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return DoranQDto.builder()
			.DoranQNo(rs.getInt("doran_q_no"))
			.DorqnQmemNo(rs.getInt("doran_q_mem_no"))
			.DoranQAdminNo(rs.getInt("doran_q_admin_no"))
			.DoranQTitle(rs.getString("doran_q_title"))
			.DoranQContent(rs.getString("doran_q_content"))
			.DoranQWriteTime(rs.getDate("doran_q_write_time"))
			.DoranQType(rs.getString("doran_q_type"))
			.DoranQProcessing(rs.getString("droan_q_processing"))
			.build();
		}
	};
	
	@Override
	public List<DoranQDto> selectList() {
		String sql = "select * from doran_q order by doran_q_no asc";
		return jdbcTemplate.query(sql, mapper);
	}

	@Override
	public List<DoranQDto> selectList(String type, String keyword) {
		String sql = "select * from doran_q where instr(#1,?)>0 order by #1 asc";
		sql=sql.replace("#1", type);
		Object[]param= {keyword};
		return jdbcTemplate.query(sql, mapper,param);
	}

	@Override
	public void clear() {
		String sql = "delete doran_q";
		jdbcTemplate.update(sql);
		
	}
}
