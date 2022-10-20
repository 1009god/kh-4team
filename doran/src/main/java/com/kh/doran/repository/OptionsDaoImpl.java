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

import com.kh.doran.entity.OptionsDto;

@Repository
public class OptionsDaoImpl implements OptionsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<OptionsDto> mapper=new RowMapper<OptionsDto>() {

		@Override
		public OptionsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return OptionsDto.builder()
									.optionsNo(rs.getInt("OPTIONS_NO"))
									.optionsPjNo(rs.getInt("OPTIONS_PJ_NO"))
									.optionsName(rs.getString("OPTIONS_NAME"))
									.optionsPrice(rs.getInt("OPTIONS_PRICE"))
									.optionsStock(rs.getInt("OPTIONS_STOCK"))
									.optionsDeliveryPrice(rs.getInt("OPTIONS_DELIVERY_PRICE"))
									.build();
		}	
	};
	
	private ResultSetExtractor<OptionsDto> extractor=new ResultSetExtractor<OptionsDto>() {

		@Override
		public OptionsDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				return OptionsDto.builder()
						.optionsNo(rs.getInt("OPTIONS_NO"))
						.optionsPjNo(rs.getInt("OPTIONS_PJ_NO"))
						.optionsName(rs.getString("OPTIONS_NAME"))
						.optionsPrice(rs.getInt("OPTIONS_PRICE"))
						.optionsStock(rs.getInt("OPTIONS_STOCK"))
						.optionsDeliveryPrice(rs.getInt("OPTIONS_DELIVERY_PRICE"))
						.build();
			}
			else {
				return null;
			}
		}
		
		
	};
	
	@Override
	public List<OptionsDto> selectList(int optionsPjNo) {
		String sql="select*from options where options_pj_no=? order by options_no";
		Object[] param= {optionsPjNo};
		return jdbcTemplate.query(sql,mapper,param);
	}
	
	@Override
	public OptionsDto selectOne(int optionsNo) {
		String sql="select*from options where options_no=?";
		Object[] param= {optionsNo};
		return jdbcTemplate.query(sql, extractor, param);
	}
}