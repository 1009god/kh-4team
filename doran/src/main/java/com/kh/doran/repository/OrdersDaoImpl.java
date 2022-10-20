package com.kh.doran.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.doran.entity.OrdersDto;

@Repository
public class OrdersDaoImpl {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<OrdersDto> mapper=new RowMapper<OrdersDto>() {

		@Override
		public OrdersDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return OrdersDto.builder()
									.ordersNo(rs.getInt("ORDERS_NO"))
									.ordersMemNo(rs.getInt("ORDERS_MEM_NO"))
									.ordersOptionsNo(rs.getInt("ORDERS_OPTIONS_NO"))
									.ordersDate(rs.getDate("ORDERS_DATE"))
									.ordersCancelDate(rs.getDate("ORDERS_CANCEL_DATE"))
									.ordersMessage(rs.getString("ORDERS_MESSAGE"))
									.ordersPayDate(rs.getDate("ORDERS_PAY_DATE"))
									.ordersDeliveryPay(rs.getInt("ORDERS_DELIVERY_PAY"))
									.ordersAddressNo(rs.getInt("ORDERS_ADDRESS_NO"))
									.build();
		}	
	};
	
	private ResultSetExtractor<OrdersDto> extractor=new ResultSetExtractor<OrdersDto>() {

		@Override
		public OrdersDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				return OrdersDto.builder()
						.ordersNo(rs.getInt("ORDERS_NO"))
						.ordersMemNo(rs.getInt("ORDERS_MEM_NO"))
						.ordersOptionsNo(rs.getInt("ORDERS_OPTIONS_NO"))
						.ordersDate(rs.getDate("ORDERS_DATE"))
						.ordersCancelDate(rs.getDate("ORDERS_CANCEL_DATE"))
						.ordersMessage(rs.getString("ORDERS_MESSAGE"))
						.ordersPayDate(rs.getDate("ORDERS_PAY_DATE"))
						.ordersDeliveryPay(rs.getInt("ORDERS_DELIVERY_PAY"))
						.ordersAddressNo(rs.getInt("ORDERS_ADDRESS_NO"))
						.build();
			}
			else {
				return null;
			}
		}
	};

}