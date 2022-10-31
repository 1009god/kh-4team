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

import com.kh.doran.entity.OrdersDto;
import com.kh.doran.vo.CreatedDetailVO;
import com.kh.doran.vo.OrdersMemNoSearchVO;
import com.kh.doran.vo.SupportDetailVO;
import com.kh.doran.vo.SupportListVO;


@Repository
public class OrdersDaoImpl implements OrdersDao{
	
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


	@Override
	public void insert(OrdersDto ordersDto) {
		String sql="insert into orders(orders_no, orders_mem_no, orders_options_no, orders_date, orders_cancel_date, orders_message, "
				+ "orders_pay_date, orders_delivery_pay, orders_address_no) "
				+ "values(orders_seq.nextval, ?, ?, sysdate, null, ?, ?, ?, ?)";
		Object[] param= {ordersDto.getOrdersMemNo(),
									ordersDto.getOrdersOptionsNo(),
									ordersDto.getOrdersMessage(),
									ordersDto.getOrdersPayDate(),
									ordersDto.getOrdersDeliveryPay(),
									ordersDto.getOrdersAddressNo()};
		jdbcTemplate.update(sql, param);
	}

	private RowMapper<OrdersMemNoSearchVO> memNoSearchMapper=new RowMapper<OrdersMemNoSearchVO>() {

		@Override
		public OrdersMemNoSearchVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return OrdersMemNoSearchVO.builder()
														.pjNo(rs.getInt("PJ_NO"))
														.pjName(rs.getString("PJ_NAME"))
														.optionsNo(rs.getInt("OPTIONS_NO"))
														.optionsName(rs.getString("OPTIONS_NAME"))
														.optionsPrice(rs.getInt("OPTIONS_PRICE"))
														.optionsStock(rs.getInt("OPTIONS_STOCK"))
														.optionsDeliveryPrice(rs.getInt("OPTIONS_DELIVERY_PRICE"))
														.ordersNo(rs.getInt("ORDERS_NO"))
														.ordersMemNo(rs.getInt("ORDERS_MEM_NO"))
														.build();
		}	
	};
	
	
	private ResultSetExtractor<OrdersMemNoSearchVO> memNoSearchExtractor=new ResultSetExtractor<OrdersMemNoSearchVO>() {

		@Override
		public OrdersMemNoSearchVO extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				return OrdersMemNoSearchVO.builder()
						.pjNo(rs.getInt("PJ_NO"))
						.pjName(rs.getString("PJ_NAME"))
						.optionsNo(rs.getInt("OPTIONS_NO"))
						.optionsName(rs.getString("OPTIONS_NAME"))
						.optionsPrice(rs.getInt("OPTIONS_PRICE"))
						.optionsStock(rs.getInt("OPTIONS_STOCK"))
						.optionsDeliveryPrice(rs.getInt("OPTIONS_DELIVERY_PRICE"))
						.ordersNo(rs.getInt("ORDERS_NO"))
						.ordersMemNo(rs.getInt("ORDERS_MEM_NO"))
						.build();
			}
			else {
				return null;
			}
		}
	};
	
@Override
public List<OrdersMemNoSearchVO> memNoSearch(int ordersMemNo) {
	String sql="select*from (select p.pj_no, p.pj_name, op.options_no, "
			+ "op.options_name, op.options_price, op.options_stock, "
			+ "op.options_delivery_price, ord.orders_no, "
			+ "ord.orders_mem_no "
			+ "from pj p, options op, orders ord "
			+ "where p.pj_no=op.options_pj_no "
			+ "and op.options_no=ord.orders_options_no) "
			+ "where orders_mem_no=?";
	Object[] param= {ordersMemNo};
	return jdbcTemplate.query(sql, memNoSearchMapper, param);
}

//후원(support list) 목록 뽑는 mapper
	private RowMapper<SupportListVO> supportListMapper = new RowMapper<SupportListVO>() {
		
		@Override
		public SupportListVO mapRow(ResultSet rs, int rowNum) throws SQLException {	
			
			return SupportListVO.builder()
					.ordersNo(rs.getInt("orders_no"))
					.ordersMemNo(rs.getInt("orders_mem_no"))
					.pjNo(rs.getInt("pj_no"))
					.pjSellerMemNo(rs.getInt("pj_seller_mem_no"))
					.pjCategory(rs.getString("pj_category"))
					.pjName(rs.getString("pj_name"))
					.memNick(rs.getString("mem_nick"))
					.build();
		}		
		
	};

// 후원 목록 (support list) 
@Override
public List<SupportListVO> selectSupportList(int ordersMemNo) {
	String sql = "select * from support_list_view where ORDERS_MEM_NO = ?";
	Object[] param = {ordersMemNo};
	return jdbcTemplate.query(sql, supportListMapper, param);
}


//후원 내역 상세 extractor
private ResultSetExtractor<SupportListVO> SupportDetailPjExtractor = new ResultSetExtractor<SupportListVO>() {
	
	@Override
	public SupportListVO extractData(ResultSet rs) throws SQLException, DataAccessException {	
		if(rs.next()) {
			return SupportListVO.builder()
					.ordersNo(rs.getInt("orders_no"))
					.ordersMemNo(rs.getInt("orders_mem_no"))
					.pjNo(rs.getInt("pj_no"))
					.pjSellerMemNo(rs.getInt("pj_seller_mem_no"))
					.pjCategory(rs.getString("pj_category"))
					.pjName(rs.getString("pj_name"))
					.memNick(rs.getString("mem_nick"))
					.build();
			}
			else {
				return null;
				}
			}
		};

		
//후원 내역 상세-프로젝트 정보
@Override
public SupportListVO selectSupportDetail(int  ordersNo) {
	String sql = "select * from support_list_view where orders_no = ?";
	Object[] param = {ordersNo};
	return jdbcTemplate.query(sql, SupportDetailPjExtractor, param);
}

//후원 내역 상세 extractor
private ResultSetExtractor<SupportDetailVO> SupportDetailExtractor = new ResultSetExtractor<SupportDetailVO>() {
	
	@Override
	public SupportDetailVO extractData(ResultSet rs) throws SQLException, DataAccessException {
		if(rs.next()) {
		return SupportDetailVO.builder()
				.ordersNo(rs.getInt("ORDERS_NO"))
				.ordersDate(rs.getDate("ORDERS_DATE"))
				.pjFundingEndDate(rs.getDate("PJ_FUNDING_END_DATE"))
				.optionsName(rs.getString("OPTIONS_NAME"))
				.optionsPrice(rs.getInt("OPTIONS_PRICE"))
				.optionsDeliveryPrice(rs.getInt("OPTIONS_DELIVERY_PRICE"))
				.addressNo(rs.getInt("ADDRESS_NO"))
				.addressMemNo(rs.getInt("ADDRESS_MEM_NO"))
				.addressName(rs.getString("ADDRESS_NAME"))
				.addressTel(rs.getString("ADDRESS_TEL"))
				.addressPost(rs.getString("ADDRESS_POST"))
				.addressBasic(rs.getString("ADDRESS_BASIC"))
				.addressDetail(rs.getString("ADDRESS_DETAIL"))
				.build();
		}
		else {
			return null;
			}
		}	
};

//후원 내역 상세- 주문, 옵션, 배송지 정보
@Override
public SupportDetailVO selectSupportDetail2(int ordersNo) {
	String sql = "select * from support_detail_view where orders_no = ?";
	Object[] param = {ordersNo};
	return jdbcTemplate.query(sql, SupportDetailExtractor, param);
}

//주문취소메소드
@Override
public boolean orderCancel(int ordersNo) {
	String sql="update orders set orders_cancel_date=sysdate where orders_no=?";
	Object[] param= {ordersNo};
	return jdbcTemplate.update(sql,param)>0;
}


//내가 개설한 프로젝트 디테일 mapper
private RowMapper<CreatedDetailVO> createdDetailMapper = new RowMapper<CreatedDetailVO>() {
	
	@Override
	public CreatedDetailVO mapRow(ResultSet rs, int rowNum) throws SQLException {	
		
		return CreatedDetailVO.builder()
										.ordersNo(rs.getInt("orders_no"))
										.ordersMemNo(rs.getInt("orders_mem_no"))
										.ordersOptionsNo(rs.getInt("orders_options_no"))
										.ordersDate(rs.getDate("orders_date"))
										.ordersCancelDate(rs.getDate("orders_cancel_date"))
										.ordersMessage(rs.getString("orders_message"))
										.ordersPayDate(rs.getDate("orders_pay_date"))
										.ordersDeliveryPay(rs.getInt("orders_delivery_pay"))
										.ordersAddressNo(rs.getInt("orders_address_no"))
										.optionsNo(rs.getInt("options_no"))
										.optionsPjNo(rs.getInt("options_pj_no"))
										.optionsName(rs.getString("options_name"))
										.optionsPrice(rs.getInt("options_price"))
										.addressNo(rs.getInt("address_no"))
										.addressMemNo(rs.getInt("address_mem_no"))
										.addressName(rs.getString("address_name"))
										.addressTel(rs.getString("address_tel"))
										.addressPost(rs.getString("address_post"))
										.addressBasic(rs.getString("address_basic"))
										.addressDetail(rs.getString("address_detail"))
										.build();
	}		
	
};


//createdDetailVO 익스트랙터
private ResultSetExtractor<CreatedDetailVO> createdDetailExtractor = new ResultSetExtractor<CreatedDetailVO>() {
	
	@Override
	public CreatedDetailVO extractData(ResultSet rs) throws SQLException, DataAccessException {
		if(rs.next()) {
			return CreatedDetailVO.builder()
					.ordersNo(rs.getInt("orders_no"))
					.ordersMemNo(rs.getInt("orders_mem_no"))
					.ordersOptionsNo(rs.getInt("orders_options_no"))
					.ordersDate(rs.getDate("orders_date"))
					.ordersCancelDate(rs.getDate("orders_cancel_date"))
					.ordersMessage(rs.getString("orders_message"))
					.ordersPayDate(rs.getDate("orders_pay_date"))
					.ordersDeliveryPay(rs.getInt("orders_delivery_pay"))
					.ordersAddressNo(rs.getInt("orders_address_no"))
					.optionsNo(rs.getInt("options_no"))
					.optionsPjNo(rs.getInt("options_pj_no"))
					.optionsName(rs.getString("options_name"))
					.optionsPrice(rs.getInt("options_price"))
					.addressNo(rs.getInt("address_no"))
					.addressMemNo(rs.getInt("address_mem_no"))
					.addressName(rs.getString("address_name"))
					.addressTel(rs.getString("address_tel"))
					.addressPost(rs.getString("address_post"))
					.addressBasic(rs.getString("address_basic"))
					.addressDetail(rs.getString("address_detail"))					
					.build();
		}
		else {
			return null;
			}
		}	
};


//특정 유저가->개설한 프로젝트들 중에서->하나를 골라서 그 프로젝트에 들어온 주문 검색하기
//상품명도 같이 나오게 추가
@Override
public List<CreatedDetailVO> selectCreatedDetail(int optionsPjNo) {
	String sql="select*from (select orders.orders_no, orders.orders_mem_no, orders.orders_options_no, orders.orders_date, orders.orders_cancel_date, orders.orders_message, orders.orders_pay_date, orders.orders_delivery_pay, orders.orders_address_no, options.options_no, options.options_pj_no, options.options_name, options.options_price, address.address_no, address.address_mem_no, address.address_name, address.address_tel, address.address_post, address.address_basic, address.address_detail from orders orders, options options, address address where orders.orders_options_no=options.options_no and orders.orders_mem_no=address.address_mem_no) where options_pj_no=?";
	Object[] param= {optionsPjNo};
	return jdbcTemplate.query(sql, createdDetailMapper, param);
}


}
	

